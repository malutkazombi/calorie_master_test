package com.example.myapplication
import android.content.Context
import androidx.compose.ui.viewinterop.AndroidView
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

class ScanFoodActivity : ComponentActivity() {

    private lateinit var barcodeScanner: BarcodeScanner
    @androidx.camera.core.ExperimentalGetImage
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Инициализация ML Kit Barcode Scanner
        barcodeScanner = BarcodeScanning.getClient()

        // Инициализация UI
        setContent {
            ScanFoodScreen(barcodeScanner)
        }
    }

    override fun onPause() {
        super.onPause()
        // Освобождение ресурсов при паузе
        ProcessCameraProvider.getInstance(this).get().unbindAll()
    }
}
@androidx.camera.core.ExperimentalGetImage
@Composable
fun ScanFoodScreen(barcodeScanner: BarcodeScanner) {
    val context = LocalContext.current
    var previewView: PreviewView? by remember { mutableStateOf(null) }

    // UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Scan Food", style = MaterialTheme.typography.titleLarge)

        // Интеграция PreviewView с Compose
        AndroidView(
            factory = { context ->
                PreviewView(context).apply {
                    previewView = this
                    this.scaleType = PreviewView.ScaleType.FILL_CENTER
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }

    // Старт камеры и привязка к PreviewView
    LaunchedEffect(previewView) {
        previewView?.let { view ->
            startCamera(context, view, barcodeScanner)
        }
    }
}
@androidx.camera.core.ExperimentalGetImage
private fun startCamera(context: Context, previewView: PreviewView, barcodeScanner: BarcodeScanner) {
    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
    cameraProviderFuture.addListener({
        val cameraProvider = cameraProviderFuture.get()

        // Настройка Preview
        val preview = Preview.Builder().build()

        // Настройка ImageAnalysis для сканирования штрих-кодов
        val imageAnalysis = ImageAnalysis.Builder()
            .setTargetRotation(context.resources.configuration.orientation)
            .build()

        imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(context)) { imageProxy ->
            val mediaImage = imageProxy.image
            if (mediaImage != null) {
                val inputImage = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

                barcodeScanner.process(inputImage)
                    .addOnSuccessListener { barcodes ->
                        for (barcode in barcodes) {
                            val rawValue = barcode.rawValue
                            // Обработка результата
                            Toast.makeText(context, "Scanned: $rawValue", Toast.LENGTH_SHORT).show()
                        }
                    }
                    .addOnFailureListener {
                        Toast.makeText(context, "Error scanning barcode", Toast.LENGTH_SHORT).show()
                    }
                    .addOnCompleteListener {
                        imageProxy.close()
                    }
            }
        }

// Запуск камеры с использованием ProcessCameraProvider
        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
        try {
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                context as ComponentActivity, cameraSelector, preview, imageAnalysis
            )
            preview.setSurfaceProvider(previewView.surfaceProvider)
        } catch (e: Exception) {
            Toast.makeText(context, "Error starting camera", Toast.LENGTH_SHORT).show()
        }
    }, ContextCompat.getMainExecutor(context))
}