package com.example.caloriemaster

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.caloriemaster.ui.components.FoodTypeCircle
import coil.compose.rememberAsyncImagePainter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CallorieMasterApp()  // Вызов главного компонента приложения
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CallorieMasterApp() {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Callorie Master") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFFEBE8DE), Color(0xFFB0AEB5))
                    )
                ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    launcher.launch("image/*")
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.camera),
                        contentDescription = "Camera icon",
                        modifier = Modifier.size(24.dp)
                    )
                }

                // Отображение выбранного изображения
                selectedImageUri?.let { uri ->
                    Image(
                        painter = rememberAsyncImagePainter(uri),
                        contentDescription = "Selected Image",
                        modifier = Modifier
                            .size(100.dp)
                            .border(2.dp, Color.Gray)
                    )
                }

                var name by remember { mutableStateOf(TextFieldValue("Ваше имя")) }
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    shape = RoundedCornerShape(8.dp)
                )

                Button(
                    onClick = { /* TODO: Implement Progress Tracking */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8E24AA))
                ) {
                    Text("進捗追跡", color = Color.White)
                }
            }

            // Кнопки сканирования еды и просмотра истории
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = { /* TODO: Implement Scan Food */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF26A69A))
                ) {
                    Text("Scan Food", color = Color.White)
                }
                Button(
                    onClick = { /* TODO: Implement View History */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF26A69A))
                ) {
                    Text("View History", color = Color.White)
                }
            }

            // Прямоугольники с кружками выбора типа еды и предложениями рецептов
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .background(Color.LightGray, RoundedCornerShape(8.dp))
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Кружки для выбора типа еды
                            FoodTypeCircle("Мясо")
                            FoodTypeCircle("Овощи")
                            FoodTypeCircle("Фрукты")
                            FoodTypeCircle("Зерновые")
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .width(120.dp)
                        .height(120.dp)
                        .background(Color.LightGray, RoundedCornerShape(8.dp))
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("レシピ 提案")  // Предложения рецептов
                        Text("進捗")  // Отслеживание прогресса
                    }
                }
            }

            // Список истории еды
            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                // Круг для отображения эффективности
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.Gray, RoundedCornerShape(50.dp))
                        .align(Alignment.CenterHorizontally),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Эффективность",
                        color = Color.White,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }

    @Composable
    fun DefaultPreview() {
        CallorieMasterApp()  // Предпросмотр главного компонента приложения
    }
}
