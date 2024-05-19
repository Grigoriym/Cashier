package com.grappim.feature.auth.presentation.model

sealed class BiometricsDialogClickState {
    data object Positive : BiometricsDialogClickState()
    data object Negative : BiometricsDialogClickState()
}
