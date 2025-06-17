package com.example.myapplication.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun FoodTypeCircle(label: String) {
    val backgroundColor = when (label) {
        "肉" -> Color(0xFFF44336) // Красный для мяса
        "野菜" -> Color(0xFF66BB6A) // Зеленый для овощей
        "果物" -> Color(0xFFFFC107) // Желтый для фруктов
        else -> Color.Gray
    }

    Box(
        modifier = Modifier
            .size(60.dp)
            .background(backgroundColor, RoundedCornerShape(30.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            style = TextStyle(
                color = Color.White,
                fontSize = 14.sp
            )
        )
    }
}

