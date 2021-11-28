package com.grappim.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grappim.uikit.theme.CashierBlue
import com.grappim.uikit.theme.CashierGray
import com.grappim.uikit.theme.CashierGreen
import com.grappim.uikit.theme.CashierTheme

@Composable
internal fun AuthScreen(
    onSignInClick: () -> Unit,
    onRegisterClick: () -> Unit,
    phone: String,
    setPhone: (String) -> Unit,
    password: String,
    setPassword: (String) -> Unit,
    isPhoneFullyEntered: Boolean
) {
    Scaffold(
        modifier = Modifier,
    ) {
        AuthScreenContent(
            modifier = Modifier.padding(it),
            phoneText = phone,
            phoneSetText = setPhone,
            password = password,
            passwordSetText = setPassword,
            isPhoneFullyEntered = isPhoneFullyEntered,
            onRegisterClick = onRegisterClick,
            onSignInClick = onSignInClick
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
    isPhoneFullyEntered: Boolean,
    onRegisterClick: () -> Unit,
    onSignInClick: () -> Unit,
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
            Spacer(modifier = Modifier.height(25.dp))
        }
        item {
            TextFieldsComponent(
                phoneText = phoneText,
                phoneSetText = phoneSetText,
                password = password,
                passwordSetText = passwordSetText,
                isPhoneFullyEntered = isPhoneFullyEntered,
                onPasswordDone = onSignInClick
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            SignInButton(
                onSignInClick = onSignInClick,
                isPhoneFullyEntered = isPhoneFullyEntered
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            SignUpSegment(
                modifier = Modifier
                    .padding(
                        bottom = 16.dp,
                    ),
                onRegisterClick = onRegisterClick
            )
        }
    }
}

@Composable
private fun TextFieldsComponent(
    phoneText: String,
    phoneSetText: (String) -> Unit,
    password: String,
    passwordSetText: (String) -> Unit,
    isPhoneFullyEntered: Boolean,
    onPasswordDone: () -> Unit
) {
    Column {
        PhoneNumberTextFieldComposable(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp
                ),
            text = phoneText,
            onTextChange = phoneSetText,
            isPhoneFullyEntered = isPhoneFullyEntered
        )

        Spacer(modifier = Modifier.height(16.dp))

        PasswordTextField(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp
                ),
            password = password,
            onPasswordChange = passwordSetText,
            onImeAction = onPasswordDone
        )
    }
}

@Composable
private fun SignInButton(
    onSignInClick: () -> Unit,
    isPhoneFullyEntered: Boolean
) {
    Button(
        onClick = onSignInClick,
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
        shape = RoundedCornerShape(25.dp),
        enabled = isPhoneFullyEntered
    ) {
        Text(
            text = stringResource(id = R.string.action_sign_in),
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
private fun SignUpSegment(
    modifier: Modifier = Modifier,
    onRegisterClick: () -> Unit
) {
    Button(
        onClick = onRegisterClick,
        modifier = modifier
            .fillMaxWidth(
                fraction = 0.8f
            )
            .padding(
                start = 32.dp,
                end = 32.dp,
                top = 16.dp
            ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White
        ),
        shape = RoundedCornerShape(25.dp)
    ) {
        Text(
            text = stringResource(id = R.string.title_sign_up),
            color = CashierBlue,
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
private fun PhoneNumberTextFieldComposable(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    isPhoneFullyEntered: Boolean
) {
    val focusManager = LocalFocusManager.current
    TextField(
        value = text,
        onValueChange = onTextChange,
        singleLine = true,
        modifier = modifier
            .fillMaxWidth(),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Phone,
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
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Number
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
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
        modifier = modifier.fillMaxWidth(),
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
@Preview(
    showBackground = true
)
private fun TextFieldsComponentPreview() {
    CashierTheme {
        TextFieldsComponent(
            phoneText = "",
            phoneSetText = {},
            password = "",
            passwordSetText = {},
            isPhoneFullyEntered = true,
            onPasswordDone = {}
        )
    }
}

@Composable
@Preview
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
@Preview
private fun PhoneNumberTextFieldComposablePreview() {
    CashierTheme {
        PhoneNumberTextFieldComposable(
            text = "",
            onTextChange = {},
            isPhoneFullyEntered = true
        )
    }
}

@Composable
@Preview
private fun AuthScreenPreview() {
    CashierTheme {
        AuthScreen(
            onSignInClick = {},
            onRegisterClick = {},
            phone = "",
            setPhone = {},
            password = "",
            setPassword = {},
            isPhoneFullyEntered = false
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun AuthScreenContentPreview() {
    CashierTheme {
        AuthScreenContent(
            modifier = Modifier,
            phoneText = "",
            phoneSetText = {},
            password = "",
            passwordSetText = {},
            isPhoneFullyEntered = false,
            onRegisterClick = {},
            onSignInClick = {}
        )
    }
}