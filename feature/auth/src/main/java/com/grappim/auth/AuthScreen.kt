package com.grappim.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
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
import com.grappim.uikit.theme.CashierGreen
import com.grappim.uikit.theme.CashierTheme

@Composable
fun AuthScreen(
    onSignInClick: (AuthTextFieldsData) -> Unit
) {
    val (phoneText, phoneSetText) = remember {
        mutableStateOf("")
    }

    val (password, setPassword) = remember {
        mutableStateOf("")
    }
    val isPhoneFullyEntered = phoneText.length == 10

    Scaffold(
        modifier = Modifier,
        bottomBar = {
            com.grappim.uikit.compose.BigActionButtonCompose(
                buttonText = stringResource(id = R.string.action_sign_in),
                modifier = Modifier,
                onButtonClick = {
                    onSignInClick(
                        AuthTextFieldsData(
                            phone = phoneText,
                            password = password
                        )
                    )
                },
                isEnabled = isPhoneFullyEntered
            )
        }
    ) {
        AuthScreenContent(
            modifier = Modifier.padding(it),
            phoneText = phoneText,
            phoneSetText = phoneSetText,
            password = password,
            passwordSetText = setPassword,
            isPhoneFullyEntered = isPhoneFullyEntered
        )
    }
}

@Composable
private fun AuthScreenContent(
    modifier: Modifier = Modifier,
    phoneText: String,
    phoneSetText: (String) -> Unit,
    password: String,
    passwordSetText: (String) -> Unit,
    isPhoneFullyEntered: Boolean
) {
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = listState
    ) {
        item {
            Spacer(modifier = Modifier.height(64.dp))
        }
        item {
            Image(
                modifier = Modifier,
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "Logo"
            )
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Text(
                modifier = Modifier,
                text = stringResource(id = R.string.action_sign_in),
                fontSize = 24.sp,
                style = TextStyle.Default.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color.Black
            )
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Text(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        end = 16.dp
                    ),
                text = stringResource(id = R.string.auth_number_hint),
                fontSize = 14.sp,
                color = CashierGray,
                textAlign = TextAlign.Center
            )
        }
        item {
            Spacer(modifier = Modifier.height(25.dp))
        }
        item {
            PhoneNumberTextFieldComposable(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        end = 16.dp
                    ),
                text = phoneText,
                onTextChange = phoneSetText,
                onImeAction = {},
                isPhoneFullyEntered = isPhoneFullyEntered
            )
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            PasswordTextField(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        end = 16.dp
                    ),
                password = password,
                onPasswordChange = passwordSetText,
                onImeAction = {}
            )
        }
    }
}

@Composable
fun PhoneNumberTextFieldComposable(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit,
    isPhoneFullyEntered: Boolean
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = text,
        onValueChange = onTextChange,
        singleLine = true,
        modifier = modifier
            .fillMaxWidth(),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_local_phone_24),
                contentDescription = "",
                tint = CashierBlue
            )
        },
        trailingIcon = {
            if (isPhoneFullyEntered) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_check_circle_green),
                    contentDescription = "",
                    tint = CashierGreen
                )
            }
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
fun PasswordTextField(
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
        modifier = modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_lock_24),
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
@Preview
fun PasswordTextFieldPreview() {
    CashierTheme {
        com.grappim.auth.PasswordTextField(
            password = "",
            onPasswordChange = {},
            onImeAction = {}
        )
    }
}

@Composable
@Preview
fun PhoneNumberTextFieldComposablePreview() {
    CashierTheme {
        com.grappim.auth.PhoneNumberTextFieldComposable(
            text = "",
            onTextChange = {},
            onImeAction = {},
            isPhoneFullyEntered = true
        )
    }
}

@Composable
@Preview
fun AuthScreenPreview() {
    CashierTheme {
        com.grappim.auth.AuthScreen(
            onSignInClick = {}
        )
    }
}

@Composable
@Preview(showBackground = true)
fun AuthScreenContentPreview() {
    CashierTheme {
        com.grappim.auth.AuthScreenContent(
            modifier = Modifier,
            phoneText = "",
            phoneSetText = {},
            password = "",
            passwordSetText = {},
            isPhoneFullyEntered = false
        )
    }
}