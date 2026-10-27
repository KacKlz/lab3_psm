package com.example.konspekt3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.konspekt3.ui.theme.Konspekt3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Konspekt3Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    JokeScreen()
                }
            }
        }
    }
}

@Composable
fun JokeScreen() {
    val jokes = stringArrayResource(id = R.array.jokes_array)

    var currentIndex by remember { mutableIntStateOf(0) }
    val currentJoke = jokes.getOrElse(currentIndex) { "Brak żartu" }

    val greetingText = stringResource(id = R.string.greeting, "Student", currentIndex + 1)

    val counterText = pluralStringResource(id = R.plurals.joke_counter, count = jokes.size, jokes.size)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = greetingText, style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Box(modifier = Modifier.padding(16.dp)) {
                Text(text = currentJoke, style = MaterialTheme.typography.bodyLarge)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = counterText, style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            currentIndex = jokes.indices.random()
        }) {
            Text(text = "Pokaż losowy żart")
        }
    }
}