plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimAndroidPlugin)
}

android {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.AndroidX.Compose.core
    }
}

dependencies {
    implementation(Deps.Compose.ui)
    implementation(Deps.Compose.material)
    implementation(Deps.Compose.toolingPreview)
    implementation(Deps.Compose.runtimeLivedata)
    implementation(Deps.Compose.foundation)
    implementation(Deps.Compose.icons)
    implementation(Deps.Compose.lifecycleViewModel)
}