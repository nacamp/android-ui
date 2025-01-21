package com.nacamp.androidui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nacamp.androidui.kbank.withAlpha
import com.nacamp.androidui.ui.theme.AndroidUITheme



@Composable
fun StyledCard(
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    colors: CardColors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        contentColor =MaterialTheme.colorScheme.onSurface,
    ),
    shape: RoundedCornerShape = RoundedCornerShape(8.dp), // 기본 모양 설정
    elevation: CardElevation = CardDefaults.cardElevation(), // 기본 elevation 설정
    content: @Composable (TextStyle) -> Unit
) {
    ProvideTextStyle(value = textStyle.copy(color = colors.contentColor)) {
        Card(
            modifier = modifier.wrapContentHeight(),
            colors = colors,
            shape = shape,
            elevation = elevation
        ) {
            val currentStyle = LocalTextStyle.current
            content(currentStyle)
        }
    }
}

@Composable
fun CardScreen(modifier: Modifier = Modifier) {
    Column {
        Card(
            modifier = Modifier.padding(16.dp),
//            shape = RoundedCornerShape(8.dp),
//            elevation = CardDefaults.cardElevation(
//                defaultElevation = 1.dp,       // 평소 상태
//                pressedElevation = 8.dp,       // 눌린 상태
//                focusedElevation = 4.dp,       // 포커스 상태
//                hoveredElevation = 3.dp,       // 마우스 호버 상태
//                draggedElevation = 8.dp       // 드래그 상태
//            ),
//            colors = CardDefaults.cardColors(
//                containerColor = Color.White,
//                contentColor = Color.Black
//            )
        ) {
            Text(
                text = "Default Card!",
                modifier = Modifier.padding(16.dp)
            )
        }
        Card(
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(10.dp),// % or .dp
            elevation = CardDefaults.cardElevation(10.dp),
            colors = CardDefaults.cardColors(
                //containerColor = Color.White,
                containerColor = Color.LightGray,
                contentColor = Color.Black
            )
        ) {
            Text(
                text = "Custom Card!",
                modifier = Modifier.padding(16.dp)
            )
        }

        StyledCard(modifier = Modifier.padding(16.dp), colors = CardDefaults.cardColors(
            //containerColor = Color.White,
            containerColor = Color.Blue,
            contentColor = Color.White
        )){
            Text(
                text = "StyledCard!",
                modifier = modifier.padding(16.dp)
            )
            Text(
                text = "StyledCard!",
                color = it.color.copy(alpha = 0.8f),
            )
            Text(
                text = "StyledCard!",
                style = it.withAlpha(0.5f),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardScreenPreview() {
    AndroidUITheme {
        CardScreen()
    }
}