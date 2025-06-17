package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.R
import com.example.myapplication.ui.theme.FoodTypeCircle



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CallorieMasterApp()
        }
    }
}
@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CallorieMasterApp() {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? -> selectedImageUri = uri }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Calorie Master",
                            style = MaterialTheme.typography.headlineMedium, // Более крупный стиль заголовка
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Фото + Имя
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray)
                            .clickable { launcher.launch("image/*") },
                        contentAlignment = Alignment.Center
                    ) {
                        if (selectedImageUri == null) {
                            Icon(Icons.Filled.Camera, contentDescription = "Выбрать фото")
                        } else {
                            Image(
                                painter = rememberAsyncImagePainter(selectedImageUri),
                                contentDescription = "Выбранное фото",
                                modifier = Modifier.clip(CircleShape)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    var name by remember { mutableStateOf(TextFieldValue("名前")) }
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Your name") },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // Кнопки действий
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = {
                        val intent = Intent(context, ScanFoodActivity::class.java)
                        context.startActivity(intent)
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("スキャン")
                }
                Button(
                    onClick = { /* TODO: Implement View History */ },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("履歴")
                }
            }

            // Категории еды
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("カテゴリー", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        FoodTypeCircle("肉")
                        FoodTypeCircle("野菜")
                        FoodTypeCircle("果物")
                    }
                }
            }

            // Кнопка эффективности
            Button(
                onClick = { /* TODO: Implement Efficiency Logic */ },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(50)
            ) {
                Text("効率", style = MaterialTheme.typography.titleLarge)
            }
        }
    }
}

