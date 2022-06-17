package com.grappim.feature.auth.presentation.model

sealed class BiometricsDialogClickState {
    object Positive : BiometricsDialogClickState()
    object Negative : BiometricsDialogClickState()
}
