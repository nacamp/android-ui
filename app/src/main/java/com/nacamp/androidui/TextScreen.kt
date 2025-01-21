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
import androidx.compose.material3.Typography
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nacamp.androidui.ui.theme.AndroidUITheme

val Typography.test: TextStyle
    get() = TextStyle(
        fontSize = 8.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 12.sp,
        letterSpacing = 0.5.sp,
        color = Color.Gray
    )

@Composable
fun TextScreen(modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "This is default text",
            modifier = modifier
        )
        Text(
            text = "This is default text",
            //text = "This is bodyLarge ...",
            modifier = modifier,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "This is labelSmall ...",
            modifier = modifier,
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = "This is test ...",
            modifier = modifier,
            style = MaterialTheme.typography.test
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextScreenPreview() {
    AndroidUITheme {
        TextScreen()
    }
}