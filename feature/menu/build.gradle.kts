plugins {
    id(Plugins.androidLibrary)
    id(Plugins.presentationPlugin)
}

android {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.AndroidX.Compose.core
    }
}