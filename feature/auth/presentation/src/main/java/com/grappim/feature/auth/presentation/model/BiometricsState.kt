package com.grappim.feature.auth.presentation.model

sealed class BiometricsState {
    object ShowPrompt : BiometricsState()
    object ShowNothing : BiometricsState()
}
