package com.example.jetpackcomposelogin

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Header(Modifier.align(Alignment.TopEnd))
        Body(Modifier.align(Alignment.Center))
        Footer(Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun Footer(modifier: Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(32.dp))
        SingUP()
        Spacer(modifier = Modifier.size(32.dp))
    }
}

@Composable
fun SingUP() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text("Don't have an account", fontSize = 12.sp,
        color = Color(0xFFB5B5B5))
        Text("Sing up", modifier = Modifier.padding(horizontal = 8.dp), fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF4EA8E9))
    }
}

@Composable
fun Body(modifier: Modifier) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var emailFormat by rememberSaveable { mutableStateOf(EmailFormat.IncorrectFormat) }
    var isLoginEnable by rememberSaveable { mutableStateOf(false) }
    Column(modifier = modifier) {
        ImageLogo(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(16.dp))
        Email(
            email,
            onTextChanged = { email = it },
            emailFormat = { emailFormat = it },
            emailFormat,
            Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Password(
            password = password,
            onTextChanged = { password = it },
            Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.size(8.dp))
        ForgotPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.size(18.dp))
        LoginButton(isLoginEnable)
        Spacer(modifier = Modifier.size(16.dp))
        LoginDivider()
        Spacer(modifier = Modifier.size(32.dp))
        SocialLogin()
    }
}

@Composable
fun SocialLogin() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.fb), contentDescription = "Social login fb",
            modifier = Modifier.size(16.dp)
        )
        Text(
            "Continue con su oreja",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp),
            color = Color(0xFF4EA8E9)
        )
    }
}

@Composable
fun LoginDivider() {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
        Text(
            "OR",
            modifier = Modifier.padding(horizontal = 18.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFB5B5B5)
        )
        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
    }
}

@Composable
fun LoginButton(loginEnable: Boolean) {
    OutlinedIconButton(
        onClick = { },
        enabled = loginEnable,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Log in")
    }
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "Forgot password?", fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF4EA8E9),
        modifier = modifier
    )
}

@Composable
fun ImageLogo(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.insta),
        contentDescription = "logo",
        modifier = modifier
    )
}

@Composable
fun Header(modifier: Modifier) {
    val activity = LocalContext.current as Activity
    Icon(imageVector = Icons.Default.Close, contentDescription = "close app",
        modifier = modifier.clickable { activity.finish() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Email(
    email: String, onTextChanged: (String) -> Unit, emailFormat: (EmailFormat) -> Unit,
    emailFormato: EmailFormat, modifier: Modifier
) {
    OutlinedTextField(
        value = email,
        modifier = Modifier.fillMaxWidth(),
        onValueChange = { onTextChanged(it) },
        label = { Text("Introduce tu email") },
        placeholder = { Text("Introduce tu email") },
        leadingIcon = { Icon(imageVector = Icons.Filled.Email, contentDescription = "IconoEmail") },
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.Clear,
                contentDescription = "Borrar",
                Modifier.clickable {
                    onTextChanged("")
                })
        },
        shape = RoundedCornerShape(67.dp),
        supportingText = {
            emailFormat(validateEmailFormat(email = email))
            if (EmailFormat.IncorrectFormat == emailFormato) {
                Row(modifier = modifier) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_error_24),
                        contentDescription = "ErrorIcono",
                        modifier = modifier
                    )
                    Text(
                        "Formato incorrecto por ejemplo edu@gmail.com", color = Color.Red,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        text = "${email.length} si",
                        modifier = Modifier.padding(10.dp)
                    )
                }
            } else {
                Text(
                    text = "${email.length}"
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password(password: String, onTextChanged: (String) -> Unit, modifier: Modifier) {
    OutlinedTextField(value = password,
        modifier = Modifier.fillMaxWidth(),
        onValueChange = { onTextChanged(it) },
        label = { Text("Introduce tu contraseña") },
        placeholder = { Text("Introduce tu contraseña") },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.baseline_lock_24),
                contentDescription = "ErrorCandado"
            )
        },
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.baseline_remove_red_eye_24),
                contentDescription = "VisualizarContraseña"
            )
        },
        shape = RoundedCornerShape(67.dp),
        supportingText = {
            Text(
                text = "${password.length}",
                textAlign = TextAlign.End,
                modifier = Modifier.padding(end = 8.dp)
            )
        }
    )
}

@Composable
fun validateEmailFormat(email: String): EmailFormat {
    return if (android.util.Patterns.EMAIL_ADDRESS.matcher(email)
            .matches()
    ) EmailFormat.CorrectFormat
    else EmailFormat.IncorrectFormat
}


enum class EmailFormat {
    IncorrectFormat,
    CorrectFormat
}




