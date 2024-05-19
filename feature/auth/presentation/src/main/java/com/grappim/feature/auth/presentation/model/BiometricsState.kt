package com.grappim.feature.auth.presentation.model

sealed class BiometricsState {
    data object ShowPrompt : BiometricsState()
    data object ShowNothing : BiometricsState()
}
