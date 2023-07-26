package com.grappim.feature.auth.presentation.ui.view

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grappim.uikit.R
import com.grappim.uikit.compose.CashierIcon
import com.grappim.uikit.compose.CashierImage
import com.grappim.uikit.compose.CashierText
import com.grappim.uikit.compose.button.CashierImageButton
import com.grappim.uikit.compose.button.CashierMediumButton
import com.grappim.uikit.compose.button.PasswordTextFieldCompose
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
    isPhoneFullyEntered: Boolean,
    onImePasswordActionDone: () -> Unit,
    onSettingsClick: () -> Unit,
    showBiometrics: Boolean,
    onShowBiometricsClick: () -> Unit
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
            onSignInClick = onSignInClick,
            onImePasswordActionDone = onImePasswordActionDone,
            onSettingsClick = onSettingsClick,
            showBiometrics = showBiometrics,
            onShowBiometricsClick = onShowBiometricsClick
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
    onImePasswordActionDone: () -> Unit,
    onSettingsClick: () -> Unit,
    showBiometrics: Boolean,
    onShowBiometricsClick: () -> Unit
) {
    val listState = rememberLazyListState()

    var isLogoRotated by rememberSaveable { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (isLogoRotated) 360f else 0f,
        animationSpec = tween(durationMillis = 1000)
    )

    LazyColumn(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = listState
    ) {
        item {
            Box(
                contentAlignment = Alignment.TopEnd,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                CashierImageButton(
                    imageVector = Icons.Filled.Settings,
                    onIconClick = onSettingsClick
                )
            }
        }
        item {
            CashierImage(
                modifier = Modifier
                    .padding(
                        top = 26.dp
                    )
                    .rotate(rotationAngle)
                    .clickable {
                        isLogoRotated = !isLogoRotated
                    },
                painter = painterResource(id = R.drawable.ic_logo)
            )
        }
        item {
            TextFieldsComponent(
                modifier = Modifier
                    .padding(
                        top = 25.dp
                    ),
                phoneText = phoneText,
                phoneSetText = phoneSetText,
                password = password,
                passwordSetText = passwordSetText,
                isPhoneFullyEntered = isPhoneFullyEntered,
                onImePasswordActionDone = onImePasswordActionDone
            )
        }

        item {
            SignInButton(
                modifier = Modifier
                    .padding(
                        top = 16.dp
                    ),
                onSignInClick = onSignInClick,
                isEnabled = isPhoneFullyEntered && password.isNotEmpty(),
                showBiometrics = showBiometrics,
                onShowBiometricsClick = onShowBiometricsClick
            )
        }
        item {
            SignUpSegment(
                modifier = Modifier
                    .padding(
                        top = 16.dp,
                        bottom = 16.dp,
                    ),
                onRegisterClick = onRegisterClick
            )
        }
//        item {
//            GuestModeSegment(
//                onGuestModeClick = onGuestModeClick
//            )
//        }
    }
}

@Composable
private fun GuestModeSegment(
    modifier: Modifier = Modifier,
    onGuestModeClick: () -> Unit
) {
    Button(
        onClick = onGuestModeClick,
        modifier = modifier
            .fillMaxWidth(
                fraction = 0.8f
            )
            .padding(
                start = 32.dp,
                end = 32.dp,
                top = 16.dp,
                bottom = 32.dp
            ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White
        ),
        shape = RoundedCornerShape(25.dp)
    ) {
        CashierText(
            text = stringResource(id = R.string.title_guest_mode),
            color = CashierBlue,
            modifier = Modifier
                .padding(
                    top = 4.dp,
                    bottom = 4.dp
                )
        )
    }
}

@Composable
private fun TextFieldsComponent(
    modifier: Modifier = Modifier,
    phoneText: String,
    phoneSetText: (String) -> Unit,
    password: String,
    passwordSetText: (String) -> Unit,
    isPhoneFullyEntered: Boolean,
    onImePasswordActionDone: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
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

        PasswordTextFieldCompose(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp
                ),
            password = password,
            onPasswordChange = passwordSetText,
            onImeAction = onImePasswordActionDone
        )
    }
}

@Composable
private fun SignInButton(
    modifier: Modifier = Modifier,
    onSignInClick: () -> Unit,
    isEnabled: Boolean,
    showBiometrics: Boolean,
    onShowBiometricsClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(
                fraction = 0.8f
            )
            .padding(
                start = 32.dp,
                end = 32.dp,
                top = 16.dp
            )
    ) {
        if (showBiometrics) {
            CashierImageButton(
                imageVector = Icons.Filled.Fingerprint,
                modifier = Modifier
                    .padding(
                        end = 8.dp
                    ),
                onIconClick = onShowBiometricsClick
            )
        }
        val textColor = if (isEnabled) {
            Color.White
        } else {
            CashierBlue
        }
        CashierMediumButton(
            onClick = onSignInClick,
            modifier = Modifier
                .fillMaxWidth(),
            backgroundColor = ButtonDefaults.buttonColors(
                disabledBackgroundColor = CashierGray
            ),
            text = stringResource(id = R.string.action_sign_in),
            enabled = isEnabled,
            textColor = textColor
        )
    }
}

@Composable
private fun SignUpSegment(
    modifier: Modifier = Modifier,
    onRegisterClick: () -> Unit
) {
    CashierMediumButton(
        text = stringResource(id = R.string.title_sign_up),
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
        backgroundColor = ButtonDefaults.buttonColors(
            backgroundColor = Color.White
        ),
        textColor = CashierBlue
    )
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
                CashierIcon(
                    imageVector = Icons.Filled.CheckCircle,
                    tint = CashierGreen
                )
            }
        },
        colors = TextFieldDefaults.textFieldColors(
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
            onImePasswordActionDone = {}
        )
    }
}

@Composable
@Preview
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
            isPhoneFullyEntered = false,
            onImePasswordActionDone = {},
            onSettingsClick = {},
            showBiometrics = true,
            onShowBiometricsClick = {}
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
            onSignInClick = {},
            onImePasswordActionDone = {},
            onSettingsClick = {},
            showBiometrics = true,
            onShowBiometricsClick = {}
        )
    }
}