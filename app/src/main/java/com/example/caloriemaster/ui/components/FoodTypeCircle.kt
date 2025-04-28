package com.example.caloriemaster.ui.components

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
    // Определим цвет для каждого типа пищи
    val backgroundColor = when (label) {
        "肉" -> Color(0xFFF44336) // Красный для мяса
        "野菜" -> Color(0xFF66BB6A) // Зеленый для овощей
        "果物" -> Color(0xFFFFC107) // Желтый для фруктов
        "穀物" -> Color(0xFF2196F3) // Синий для зерновых
        else -> Color.Gray // Если тип пищи не известен, используем серый
    }

    Box(
        modifier = Modifier
            .size(60.dp)  // Увеличим размер круга для более крупного текста
            .background(backgroundColor, RoundedCornerShape(30.dp)),  // Полукруглый цвет в зависимости от типа
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,  // Отображаем тип пищи
            style = TextStyle(
                color = Color.White,  // Белый цвет текста
                fontSize = 14.sp  // Увеличим размер шрифта для лучшей видимости
            )
        )
    }
}
