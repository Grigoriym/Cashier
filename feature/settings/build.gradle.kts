plugins {
    id(Plugins.androidLibrary)
    id(Plugins.presentationPlugin)
    id(Plugins.kotlinParcelize)
}

android {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.AndroidX.Compose.core
    }
}