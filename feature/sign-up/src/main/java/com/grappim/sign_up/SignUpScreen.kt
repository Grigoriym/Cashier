package com.grappim.sign_up

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grappim.uikit.theme.CashierBlue
import com.grappim.uikit.theme.CashierGray
import com.grappim.uikit.theme.CashierTheme

@Composable
internal fun SignUpScreen(
    onSignUpClick: () -> Unit,
    phoneText: String,
    setPhone: (String) -> Unit,
    password: String,
    setPassword: (String) -> Unit,
    email: String,
    setEmail: (String) -> Unit,
) {
    Box(
        modifier = Modifier
    ) {
        SignUpScreenContent(
            modifier = Modifier,
            phoneText = phoneText,
            phoneSetText = setPhone,
            password = password,
            passwordSetText = setPassword,
            email = email,
            setEmail = setEmail,
            onSignUpClick = onSignUpClick
        )
    }
}

@Composable
private fun SignUpScreenContent(
    modifier: Modifier = Modifier,
    phoneText: String,
    phoneSetText: (String) -> Unit,
    password: String,
    passwordSetText: (String) -> Unit,
    email: String,
    setEmail: (String) -> Unit,
    onSignUpClick: () -> Unit
) {
    val listState = rememberLazyListState()
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = 16.dp,
                end = 16.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = listState
    ) {
        item {
            HeaderText()
        }
        item {
            PhoneNumberTextFieldComposable(
                text = phoneText,
                onTextChange = phoneSetText,
                onImeAction = {},
                modifier = Modifier
            )
        }
        item {
            EmailTextFieldComposable(
                email = email,
                setEmail = setEmail
            )
        }
        item {
            PasswordTextField(
                password = password,
                onPasswordChange = passwordSetText,
                modifier = Modifier,
                onImeAction = {}
            )
        }
        item {
            SignUpButton(
                onSignUpClick = onSignUpClick
            )
        }
    }
}

@Composable
private fun HeaderText() {
    Text(
        text = stringResource(
            id = R.string.title_sign_up
        ),
        fontSize = 24.sp,
        style = TextStyle.Default.copy(
            fontWeight = FontWeight.Bold
        ),
        color = Color.Black,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(
                top = 32.dp
            )
    )
}

@Composable
private fun EmailTextFieldComposable(
    modifier: Modifier = Modifier,
    email: String,
    setEmail: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = email,
        onValueChange = setEmail,
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 16.dp
            ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = "",
                tint = CashierBlue
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(
                text = stringResource(
                    id = R.string.title_email
                ),
                fontSize = 16.sp,
                color = CashierGray
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Email
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            }
        )
    )
}

@Composable
private fun PhoneNumberTextFieldComposable(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = text,
        onValueChange = onTextChange,
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 16.dp
            ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Phone,
                contentDescription = "",
                tint = CashierBlue
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(
                text = stringResource(id = R.string.title_phone_number),
                fontSize = 16.sp,
                color = CashierGray
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Number
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onImeAction()
                keyboardController?.hide()
            }
        )
    )
}

@Composable
private fun PasswordTextField(
    modifier: Modifier = Modifier,
    password: String,
    onPasswordChange: (String) -> Unit,
    onImeAction: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var passwordVisibility by remember {
        mutableStateOf(false)
    }

    TextField(
        value = password,
        onValueChange = onPasswordChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 16.dp
            ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Lock,
                contentDescription = "",
                tint = CashierBlue
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(
                text = stringResource(id = R.string.title_password),
                fontSize = 16.sp,
                color = CashierGray
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onImeAction()
                keyboardController?.hide()
            }
        ),
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        trailingIcon = {
            val image = if (passwordVisibility) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }
            IconButton(
                onClick = { passwordVisibility = !passwordVisibility }
            ) {
                Icon(
                    imageVector = image,
                    contentDescription = ""
                )
            }
        }
    )
}

@Composable
private fun SignUpButton(
    onSignUpClick: () -> Unit
) {
    Button(
        onClick = onSignUpClick,
        modifier = Modifier
            .fillMaxWidth(
                fraction = 0.8f
            )
            .padding(
                start = 32.dp,
                end = 32.dp,
                top = 16.dp
            ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = CashierBlue
        ),
        shape = RoundedCornerShape(25.dp)
    ) {
        Text(
            text = stringResource(id = R.string.title_sign_up),
            color = Color.White,
            fontSize = 17.sp,
            modifier = Modifier
                .padding(
                    top = 4.dp,
                    bottom = 4.dp
                )
        )
    }
}

@Composable
@Preview(
    showBackground = true
)
private fun RegisterScreenContentPreview() {
    CashierTheme {
        SignUpScreenContent(
            phoneText = "",
            phoneSetText = {},
            password = "",
            passwordSetText = {},
            email = "",
            setEmail = {},
            onSignUpClick = {}
        )
    }
}

@Composable
@Preview(
    showBackground = true
)
private fun SignUpButtonPreview() {
    CashierTheme {
        SignUpButton(
            onSignUpClick = {}
        )
    }
}

@Composable
@Preview(
    showBackground = true
)
private fun PasswordTextFieldPreview() {
    CashierTheme {
        PasswordTextField(
            password = "",
            onPasswordChange = {},
            onImeAction = {}
        )
    }
}

@Composable
@Preview(
    showBackground = true
)
private fun PhoneTextFieldPreview() {
    CashierTheme {
        PhoneNumberTextFieldComposable(
            text = "",
            onTextChange = {},
            onImeAction = {}
        )
    }
}

