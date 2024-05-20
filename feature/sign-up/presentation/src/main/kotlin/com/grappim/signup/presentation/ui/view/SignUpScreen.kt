package com.grappim.signup.presentation.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grappim.cashier.core.resources.NativeText
import com.grappim.cashier.core.resources.asString
import com.grappim.signup.presentation.model.SignUpFieldsValidationData
import com.grappim.uikit.R
import com.grappim.uikit.compose.CashierIcon
import com.grappim.uikit.compose.CashierTextGray
import com.grappim.uikit.compose.CashierTextH5Text
import com.grappim.uikit.compose.button.CashierMediumButton
import com.grappim.uikit.compose.button.PasswordTextFieldCompose
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
    repeatPassword: String,
    repeatPasswordSet: (String) -> Unit,
    validationData: SignUpFieldsValidationData?
) {
    Scaffold(
        modifier = Modifier
    ) {
        SignUpScreenContent(
            modifier = Modifier.padding(it),
            phoneText = phoneText,
            phoneSetText = setPhone,
            password = password,
            passwordSetText = setPassword,
            email = email,
            setEmail = setEmail,
            onSignUpClick = onSignUpClick,
            validationData = validationData,
            repeatPassword = repeatPassword,
            repeatPasswordSet = repeatPasswordSet
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
    onSignUpClick: () -> Unit,
    repeatPassword: String,
    repeatPasswordSet: (String) -> Unit,
    validationData: SignUpFieldsValidationData?
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
                modifier = Modifier,
                validationData = validationData
            )
        }
        item {
            EmailTextFieldComposable(
                email = email,
                setEmail = setEmail,
                validationData = validationData
            )
        }
        item {
            val passwordError: NativeText? = validationData?.passwordErrorText

            PasswordTextFieldCompose(
                password = password,
                onPasswordChange = passwordSetText,
                onImeAction = {},
                isError = passwordError != null,
                errorMessage = {
                    ShowError(error = passwordError)
                }
            )
        }
        item {
            val passwordError: NativeText? = validationData?.repeatPasswordErrorText

            PasswordTextFieldCompose(
                password = repeatPassword,
                onPasswordChange = repeatPasswordSet,
                onImeAction = {},
                isError = passwordError != null,
                errorMessage = {
                    ShowError(error = passwordError)
                },
                placeholderText = stringResource(id = R.string.title_repeat_password)
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
private fun ShowError(error: NativeText?) {
    Box(
        modifier = Modifier
            .padding(
                start = 16.dp,
                end = 12.dp
            )
    ) {
        CompositionLocalProvider(
            LocalTextStyle provides LocalTextStyle.current.copy(
                fontSize = 12.sp,
                color = if (error != null) {
                    MaterialTheme.colors.error
                } else {
                    LocalTextStyle.current.color
                }
            )
        ) {
            if (error != null) {
                Text(
                    text = error.asString(LocalContext.current)
                )
            } else {
                CompositionLocalProvider(
                    LocalContentAlpha provides ContentAlpha.medium
                ) {}
            }
        }
    }
}

@Composable
private fun HeaderText() {
    CashierTextH5Text(
        text = stringResource(
            id = R.string.title_sign_up
        ),
        style = TextStyle.Default.copy(
            fontWeight = FontWeight.Bold
        ),
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
    setEmail: (String) -> Unit,
    validationData: SignUpFieldsValidationData?
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val emailError: NativeText? = validationData?.emailErrorText

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
            CashierIcon(
                imageVector = Icons.Filled.Email
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        isError = emailError != null,
        placeholder = {
            CashierTextGray(
                text = stringResource(
                    id = R.string.title_email
                )
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

    ShowError(error = emailError)
}

@Composable
private fun PhoneNumberTextFieldComposable(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit,
    validationData: SignUpFieldsValidationData?
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val phoneError: NativeText? = validationData?.phoneNumberErrorText

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
            CashierIcon(
                imageVector = Icons.Filled.Phone
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        isError = phoneError != null,
        placeholder = {
            CashierTextGray(
                text = stringResource(id = R.string.title_phone_number)
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
    ShowError(error = phoneError)
}

@Composable
private fun SignUpButton(onSignUpClick: () -> Unit) {
    CashierMediumButton(
        onClick = onSignUpClick,
        text = stringResource(id = R.string.title_sign_up),
        modifier = Modifier
            .fillMaxWidth(fraction = 0.8f)
            .padding(
                start = 32.dp,
                end = 32.dp,
                top = 16.dp
            )
    )
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
            onSignUpClick = {},
            validationData = null,
            repeatPassword = "",
            repeatPasswordSet = {}
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
        PasswordTextFieldCompose(
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
            onImeAction = {},
            validationData = null
        )
    }
}
