package com.example.myapplication.ui.theme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.dataClasses.EmailRequest
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


@Composable
fun SendEmailScreen(navController: NavController, viewModel: MainViewModel) {
    var subject by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }

    // Lista de email-uri a utilizatorilor
    val userEmails by viewModel.userEmails.observeAsState(emptyList())

    // Stare pentru a urmări email-urile selectate
    var selectedEmails = remember { mutableStateOf(mutableSetOf<String>()) }

    // Fetch utilizatori
    LaunchedEffect(Unit) {
        viewModel.fetchUsers()
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Send Email", style = MaterialTheme.typography.h4)

        Spacer(modifier = Modifier.height(16.dp))

        // Listă de email-uri din care poți selecta
        Text("Select Recipients:", style = MaterialTheme.typography.h5)
        LazyColumn(modifier = Modifier
            .weight(1f) // Ocupă spațiul rămas în ecran
            .fillMaxWidth()) {
            items(userEmails) { email ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable {
                            if (selectedEmails.value.contains(email)) {
                                selectedEmails.value.remove(email)
                            } else {
                                selectedEmails.value.add(email)
                            }
                        }
                ) {
                    Checkbox(
                        checked = selectedEmails.value.contains(email),
                        onCheckedChange = { isChecked ->
                            if (isChecked) {
                                selectedEmails.value.add(email)
                            } else {
                                selectedEmails.value.remove(email)
                            }
                        }
                    )
                    Text(text = email, style = MaterialTheme.typography.h6)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Câmp pentru subiect
        TextField(
            value = subject,
            onValueChange = { subject = it },
            label = { Text("Subject") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Câmp pentru corpul emailului
        TextField(
            value = body,
            onValueChange = { body = it },
            label = { Text("Body") },
            modifier = Modifier.fillMaxWidth().height(200.dp),
            maxLines = 5
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Buton pentru trimiterea emailului
        Button(
            onClick = {
                val emailRequest = EmailRequest(
                    recipients = selectedEmails.value.toList(),
                    subject = subject,
                    body = body
                )

                // Apelul funcției pentru trimiterea emailului
                viewModel.sendEmail(emailRequest)

                // Resetarea câmpurilor
                subject = ""
                body = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Send Email")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Buton pentru a reveni la ecranul anterior
        Button(onClick = { navController.popBackStack() }) {
            androidx.compose.material3.Text("Back to Home")
        }
    }
}
