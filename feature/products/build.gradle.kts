plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimAndroidLibraryPlugin)
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
    implementation(project(Modules.utilsCalculations))
    implementation(project(Modules.utilsDateTime))
    implementation(project(Modules.di))

    implementation(Deps.combineTupleFlow)

    implementation(Deps.Compose.ui)
    implementation(Deps.Compose.material)
    implementation(Deps.Compose.toolingPreview)
    implementation(Deps.Compose.uiTooling)
    implementation(Deps.Compose.runtime)
    implementation(Deps.Compose.runtimeLivedata)
    implementation(Deps.Compose.foundation)
    implementation(Deps.Compose.foundationLayout)
    implementation(Deps.Compose.icons)
    implementation(Deps.Compose.constraint)
    implementation(Deps.Compose.lifecycleViewModel)
}