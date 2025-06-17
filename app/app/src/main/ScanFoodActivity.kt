package com.example.myapplication
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.Analyzer
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.camera.view.PreviewView
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.camera.core.ExperimentalGetImage

class ScanFoodActivity : ComponentActivity() {

    private lateinit var cameraProvider: ProcessCameraProvider
    private lateinit var preview: androidx.camera.core.Preview
    private lateinit var imageAnalysis: ImageAnalysis
    private lateinit var barcodeScanner: BarcodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Инициализация ML Kit Barcode Scanner
        barcodeScanner = BarcodeScanning.getClient()

        // Инициализация UI
        setContent {
            ScanFoodScreen()
        }

        // Инициализация камеры
        startCamera()
    }

    @OptIn(ExperimentalGetImage::class)
    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            cameraProvider = cameraProviderFuture.get()

            // Настройка Preview
            preview = Preview.Builder().build()

            // Настройка ImageAnalysis для сканирования штрих-кодов
            imageAnalysis = ImageAnalysis.Builder()
                .setTargetRotation(windowManager.defaultDisplay.rotation)
                .build()

            imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(this)) { imageProxy ->
                // Помечаем использование getImage() как экспериментальное
                @androidx.camera.core.ExperimentalGetImage
                val mediaImage = imageProxy.image
                if (mediaImage != null) {
                    val inputImage = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

                    barcodeScanner.process(inputImage)
                        .addOnSuccessListener { barcodes ->
                            for (barcode in barcodes) {
                                val rawValue = barcode.rawValue
                                // Обработка результата
                                Toast.makeText(this, "Scanned: $rawValue", Toast.LENGTH_SHORT).show()
                            }
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "Error scanning barcode", Toast.LENGTH_SHORT).show()
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
                    this, cameraSelector, preview, imageAnalysis
                )
                preview.setSurfaceProvider(findViewById<PreviewView>(R.id.preview_view).surfaceProvider)
            } catch (e: Exception) {
                Toast.makeText(this, "Error starting camera", Toast.LENGTH_SHORT).show()
            }
        }, ContextCompat.getMainExecutor(this))
    }


    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        cameraProvider.unbindAll()
    }
}

@Composable
fun ScanFoodScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Scan Food", style = MaterialTheme.typography.titleLarge)
        // Здесь будет UI для отображения камеры или других элементов
    }
}
