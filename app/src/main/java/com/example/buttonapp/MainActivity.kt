package com.example.buttonapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.buttonapp.ui.theme.ButtonAppTheme
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseAnalytics.getInstance(this)
        enableEdgeToEdge()
        setContent {
            ButtonAppTheme {
                MyScreen()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ButtonAppTheme {
        Greeting("Android")
    }
}

fun increase(number: Int): Int {
    return number + 1
}

@Composable
fun FilledButtonExample() {
    val context = LocalContext.current
    val firebaseAnalytics = FirebaseAnalytics.getInstance(context)

    var count by remember { mutableStateOf(0) }

    Button(onClick = {
        count = increase(count)

        // Log a custom event named "filled_button_click"
        val bundle = Bundle().apply {
            putInt("click_count", count)
        }
        firebaseAnalytics.logEvent("filled_button_click", bundle)
    }) {
        Text("Filled: $count")
    }
}



//@Composable
//fun FilledButtonExample() {
//    var count by remember { mutableStateOf(0)}
//    Button(onClick = {
//        count = increase(count)
//    }) {
//        Text("Filled:$count")
//    }
//}
@Composable
fun MyScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            FilledButtonExample()
        }
    }
}