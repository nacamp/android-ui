package com.nacamp.androidui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nacamp.androidui.ui.theme.AndroidUITheme


object CButtonDefaults {
    val Shape = RoundedCornerShape(6.dp)

    @Composable
    fun colors() = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = contentColorFor(MaterialTheme.colorScheme.primary),
        disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
        disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
    )

    @Composable
    fun elevation() = ButtonDefaults.buttonElevation(
        defaultElevation = 54.dp,
        pressedElevation = 38.dp,
        focusedElevation = 2.dp,
        hoveredElevation = 6.dp,
        disabledElevation = 0.dp
    )

    val Border = BorderStroke(0.dp, Color.Transparent)

    val ContentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
}

@Composable
fun CButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = CButtonDefaults.Shape,
    colors: ButtonColors = CButtonDefaults.colors(),
    elevation: ButtonElevation = CButtonDefaults.elevation(),
    border: BorderStroke = CButtonDefaults.Border,
    contentPadding: PaddingValues = CButtonDefaults.ContentPadding,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit = {
        Text("Default Content") // 기본 콘텐츠
    }
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        enabled = enabled
    ) {
        content()
    }
}


@Composable
fun ButtonScreen(modifier: Modifier = Modifier) {
    Column {
        Button(
            onClick = { },
            modifier = Modifier.padding(2.dp),
//            enabled = false,
//            shape = RoundedCornerShape(100), // 기본값
//            colors = ButtonDefaults.buttonColors(),
//            colors = ButtonDefaults.buttonColors(
//                containerColor = MaterialTheme.colorScheme.primary,
//                contentColor = contentColorFor(MaterialTheme.colorScheme.primary),
//                disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
//                disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
//            ),
//            elevation = ButtonDefaults.buttonElevation(),
//            border = BorderStroke(0.dp, Color.Transparent),
//            contentPadding = ButtonDefaults.ContentPadding,
//            interactionSource = 상태감지
        ) {
            Text("Default")
        }

        val defaultPadding = ButtonDefaults.ContentPadding
        // 기존 패딩 값을 기반으로 새로운 PaddingValues 생성
        val adjustedPadding1 = PaddingValues(
            start = defaultPadding.calculateStartPadding(layoutDirection = LayoutDirection.Ltr) + 8.dp,
            top = defaultPadding.calculateTopPadding() - 4.dp,
            end = defaultPadding.calculateEndPadding(layoutDirection = LayoutDirection.Ltr) + 8.dp,
            bottom = defaultPadding.calculateBottomPadding() - 4.dp
        )
        val adjustedPadding2 = PaddingValues(
            start = 24.dp,
            top = 4.dp,
            end = 24.dp,
            bottom = 4.dp
        )
        Button(
            onClick = { },
            modifier = Modifier.padding(2.dp),
            enabled = true,
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = contentColorFor(MaterialTheme.colorScheme.primary),
                disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
                disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 54.dp,
                pressedElevation = 38.dp,
                focusedElevation = 2.dp,
                hoveredElevation = 6.dp,
                disabledElevation = 0.dp
            ),
            border = BorderStroke(0.dp, Color.Transparent),
            contentPadding = adjustedPadding2
        ) {
            Text("Test")
        }

        CButton(
            onClick = { },
        ) {
            Text("CButton")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ButtonScreenPreview() {
    AndroidUITheme {
        ButtonScreen()
    }
}