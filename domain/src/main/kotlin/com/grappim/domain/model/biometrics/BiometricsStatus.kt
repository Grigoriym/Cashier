package com.grappim.domain.model.biometrics

enum class BiometricsStatus {
    SET,
    NOT_SET,
    REFUSED;

    companion object {
        fun getDefault(): BiometricsStatus =
            NOT_SET

        fun from(status: String?): BiometricsStatus =
            values().find {
                it.name == status
            } ?: getDefault()
    }
}