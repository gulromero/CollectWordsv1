package com.example.collectwordsv1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.collectwordsv1.ui.theme.CollectWordsv1Theme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    setContent {
      CollectWordsv1Theme {
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colorScheme.background
        ) {
          CollectWordsApp()
        }
      }
    }
  }
}

@Composable
fun CollectWordsApp() {
  var text by remember { mutableStateOf(TextFieldValue("")) }
  var words by remember { mutableStateOf(mutableListOf<String>()) }
  var output by remember { mutableStateOf("") }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = "Collect Words",
      style = MaterialTheme.typography.headlineLarge,
      modifier = Modifier.padding(bottom = 16.dp)
    )

    // Input Field
    OutlinedTextField(
      value = text,
      onValueChange = { text = it },
      label = { Text("Enter a word") },
      modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Button Row
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceEvenly
    ) {
      Button(
        onClick = {
          words.add(text.text)
          text = TextFieldValue("")
          Log.d("CollectWords", "Word added: ${words.joinToString()}")
        },
        enabled = text.text.isNotBlank()
      ) {
        Text("Add")
      }

      Button(
        onClick = {
          words.clear()
          output = ""
          Log.d("CollectWords", "Words cleared")
        },
        enabled = words.isNotEmpty()
      ) {
        Text("Clear")
      }

      Button(
        onClick = {
          output = words.joinToString(", ")
          Log.d("CollectWords", "Words displayed: $output")
        },
        enabled = words.isNotEmpty()
      ) {
        Text("Show")
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Display output
    Text(
      text = output,
      style = MaterialTheme.typography.bodyLarge
    )
  }
}

@Preview(showBackground = true)
@Composable
fun CollectWordsAppPreview() {
  CollectWordsv1Theme {
    CollectWordsApp()
  }
}
