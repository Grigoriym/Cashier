package com.grappim.auth.model

sealed class BiometricsState {
    object ShowPrompt : BiometricsState()
    object ShowNothing : BiometricsState()
}
