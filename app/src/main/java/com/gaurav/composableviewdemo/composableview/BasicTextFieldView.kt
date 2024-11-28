package com.gaurav.composableviewdemo.composableview

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun AllTextFieldViews() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HeaderText()
        Spacer(modifier = Modifier.size(15.dp))
        GradientText()
        Spacer(modifier = Modifier.size(15.dp))
        BasicTextFieldView()
        Spacer(modifier = Modifier.size(15.dp))
        OutlinedTextFieldExample()
        Spacer(modifier = Modifier.size(15.dp))
        PasswordTextField()
        Spacer(modifier = Modifier.size(15.dp))
        NumberTextField()
        Spacer(modifier = Modifier.size(15.dp))
        SearchTextField()
        Spacer(modifier = Modifier.size(15.dp))
        MultilineTextField()
        Spacer(modifier = Modifier.size(15.dp))
        ErrorTextField()
        Spacer(modifier = Modifier.size(15.dp))
        ReadOnlyTextField()
        Spacer(modifier = Modifier.size(15.dp))
        CustomTextField()
    }
}

/**
 * This is just normal header text
 */
@Composable
fun HeaderText() {
    Text(
        text = "This is Text Field Demo",
        textAlign = TextAlign.Center,
        color = Color.Gray,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        fontSize = 24.sp,
    )
}

@Composable
fun GradientText() {
    Text(
        text = "Multiple Text Field !",
        modifier = Modifier

            .graphicsLayer(alpha = 0.99f) // Enables advanced drawing
            .background(
                Brush.horizontalGradient(
                    colors = listOf(Color(0xFF6200EA), Color(0xFF03DAC5))
                )
            )
            .padding(start = 40.dp, end = 40.dp, top = 5.dp, bottom = 5.dp),
        style = MaterialTheme.typography.headlineSmall,
        color = Color.White // Text color set to transparent to enable gradient
    )
}


/**
 * This is a basic text field
 */
@Composable
fun BasicTextFieldView() {
    var myText by remember {
        mutableStateOf("")
    }
    TextField(
        value = myText,
        onValueChange = { myText = it },
        label = { Text(text = "Enter your name") },
        placeholder = { Text(text = "Placeholder text") },
        modifier = Modifier
            .size(300.dp, 60.dp)
            .background(
                color = Color.Gray,
                shape = RoundedCornerShape(10.dp)
            ) // Apply shape to background
            .border(
                1.dp,
                color = Color.Green,
                shape = RoundedCornerShape(10.dp)
            ), // Align border with shape
        shape = RoundedCornerShape(10.dp), // Ensures TextField matches the same shape
        singleLine = true,
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent, // Removes unfocused underline
            focusedIndicatorColor = Color.Transparent // Removes focused underline
        )
    )

}

/**
 * This is a outlined text field
 */
@Composable
fun OutlinedTextFieldExample() {
    var myOutlineText by remember { mutableStateOf("") }
    OutlinedTextField(
        value = myOutlineText,
        onValueChange = { myOutlineText = it },
        modifier = Modifier.size(300.dp, 60.dp),
        label = { Text("Enter outline text") },
        placeholder = { Text("Placeholder text") },
        singleLine = true
    )
}

/**
 * This is a password text field
 */
@Composable
fun PasswordTextField() {
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    TextField(
        value = password,
        onValueChange = { password = it },
        modifier = Modifier.size(300.dp, 60.dp),
        label = { Text("Password") },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val icon = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    imageVector = icon,
                    contentDescription = if (passwordVisible) "Hide password" else "Show password"
                )
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}


/**
 * This is a number text field
 */
@Composable
fun NumberTextField() {
    var number by remember { mutableStateOf("") }
    TextField(
        value = number,
        onValueChange = { if (it.all { char -> char.isDigit() }) number = it },
        modifier = Modifier.size(300.dp, 60.dp),
        label = { Text("Enter number") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true
    )
}

/**
 * This is a search text field
 */
@Composable
fun SearchTextField() {
    var searchText by remember { mutableStateOf("") }
    TextField(
        value = searchText,
        onValueChange = { searchText = it },
        modifier = Modifier.size(300.dp, 60.dp),
        label = { Text("Search") },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
        },
        singleLine = true
    )
}

/**
 * This is a multiline text field
 */
@Composable
fun MultilineTextField() {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier.size(300.dp, 60.dp),
        label = { Text("Enter text") },
        placeholder = { Text("Multiline input") },
        maxLines = 5,
        singleLine = false
    )
}

/**
 *
 */
@Composable
fun ErrorTextField() {
    var text by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    Column {
        TextField(
            value = text,
            onValueChange = {
                text = it
                isError = text.length < 5 // Example validation
            },
            modifier = Modifier.size(300.dp, 60.dp),
            label = { Text("Username") },
            placeholder = { Text("Error $text") },
            isError = isError,
            singleLine = true
        )
        if (isError) {
            Text(
                text = "Username must be at least 5 characters",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

/**
 * This is a read only text field
 */
@Composable
fun ReadOnlyTextField() {
    TextField(
        value = "Read-only text",
        onValueChange = {},
        modifier = Modifier.size(300.dp, 60.dp),
        label = { Text("Read-only") },
        enabled = false,
        singleLine = true
    )
}

/**
 * This is a custom text field
 */
@Composable
fun CustomTextField() {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier.size(300.dp, 60.dp),
        label = { Text("Custom TextField") },
        leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
        trailingIcon = { Icon(Icons.Default.Check, contentDescription = null) },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.secondary
        ),
        shape = RoundedCornerShape(8.dp),
        singleLine = true
    )
}







