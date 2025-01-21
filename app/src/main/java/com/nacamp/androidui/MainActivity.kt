package com.nacamp.androidui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nacamp.androidui.kbank.Page1Screen
import com.nacamp.androidui.ui.theme.AndroidUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidUITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Page1Screen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Column {

        Text(
            text = "Hello jimmy!",
            modifier = modifier
        )
        Text(
            text = "Hello jimmy!",
            modifier = modifier
        )
        Button(
            onClick = {  },
            modifier = Modifier.padding(2.dp),
        ) { 
            Text("Click me")
        }
    }

}



@Composable
fun MyApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            ScrollableList(navController)
        }
        composable("case/{item}") { backStackEntry ->
            val itemString = backStackEntry.arguments?.getString("item") ?: ""
            val paths = splitString(itemString, "_")
            if (paths[0] == "today") {

                when {
                    paths[1] == "case1" -> {
                        Page1Screen()
                    }
                    else -> {
                        Page1Screen()
                    }
                }
            } else {
                Page1Screen()
            }
        }
    }
}


@Composable
fun ScrollableList(navController: NavHostController) {
    val items = arrayOf(
        "today_case1",
        "today_case2",
        "today_case3",
        "activity_case",
        "schedule_case",
        "rememberPermissionState_case",
        "cleanArchitecture_case",
        "ble_scan",
        "ble_healthcareScan",
        "ble_server",
        "room_case1",
        "config_case1",
        "firebase_case1",
        "firebase_database",
        "scroll_auto",
        "scroll_animation",
        "foreground_case1"
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(items) { item ->
            ListItem(text = item, onClick = { navController.navigate("case/$item") })
        }
    }
}

@Composable
fun ListItem(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    )
}

fun splitString(input: String, delimiter: String): List<String> {
    return input.split(delimiter)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidUITheme {
        Greeting()
    }
}