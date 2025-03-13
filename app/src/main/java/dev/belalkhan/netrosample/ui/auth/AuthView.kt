//package dev.belalkhan.netrosample.ui.auth
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material3.Button
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.text.input.TextFieldValue
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.hilt.navigation.compose.hiltViewModel
//
//@Composable
//fun AuthView(
//    viewModel: AuthViewModel = hiltViewModel(),
//    onNavigate: (String) -> Unit = {},
//) {
//    var username by remember { mutableStateOf(TextFieldValue()) }
//    var password by remember { mutableStateOf(TextFieldValue()) }
//
//    LaunchedEffect(Unit) {
//        viewModel.navigationEvent.collect { route -> onNavigate(route) }
//    }
//
//    Column(
//        modifier =
//            Modifier
//                .fillMaxSize()
//                .padding(24.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
//    ) {
//        Text(
//            text = "Welcome Back",
//            fontSize = 24.sp,
//            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
//            color = MaterialTheme.colorScheme.primary,
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        OutlinedTextField(
//            value = username,
//            onValueChange = { username = it },
//            label = { Text("Username") },
//            singleLine = true,
//            modifier = Modifier.fillMaxWidth(),
//        )
//
//        Spacer(modifier = Modifier.height(12.dp))
//
//        OutlinedTextField(
//            value = password,
//            onValueChange = { password = it },
//            label = { Text("Password") },
//            singleLine = true,
//            visualTransformation = PasswordVisualTransformation(),
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//            modifier = Modifier.fillMaxWidth(),
//        )
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        Button(
//            onClick = { viewModel.authenticate(username.text, password.text) },
//            modifier = Modifier.fillMaxWidth(),
//            shape = MaterialTheme.shapes.medium,
//        ) {
//            Text("Login")
//        }
//
//        Spacer(modifier = Modifier.height(12.dp))
//
//        TextButton(onClick = { /*TODO*/ }) {
//            Text(
//                text = "Don't have an account? Sign up",
//                color = MaterialTheme.colorScheme.primary,
//                textAlign = TextAlign.Center,
//            )
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun AuthScreenPreview() {
//    AuthView()
//}
