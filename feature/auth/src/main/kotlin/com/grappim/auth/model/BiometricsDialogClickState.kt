package com.grappim.auth.model

sealed class BiometricsDialogClickState {
    object Positive : BiometricsDialogClickState()
    object Negative : BiometricsDialogClickState()
}
