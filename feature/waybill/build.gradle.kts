plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimAndroidPlugin)
    id(Plugins.hiltAndroid)
    id(Plugins.safeArgs)
}

android {
    buildFeatures {
        viewBinding = true
        compose = true
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.AndroidX.Compose.core
    }
}

dependencies {
    implementation(project(Modules.domain))
    implementation(project(Modules.logger))
    implementation(project(Modules.uikit))
    implementation(project(Modules.utilsExtensions))
    implementation(project(Modules.core))
    implementation(project(Modules.navigation))
    implementation(project(Modules.utilsCalculations))
    implementation(project(Modules.utilsDateTime))

    coreLibraryDesugaring(Deps.desugar)


    implementation(Deps.AndroidX.lifecycleLiveData)
    implementation(Deps.AndroidX.lifecycleViewModel)
    implementation(Deps.AndroidX.lifecycleRuntime)

    implementation(Deps.zxing) {
        isTransitive = false
    }
    implementation(Deps.Google.zxingCore)

    implementation(Deps.viewBinding)

    implementation(Deps.Google.hilt)
    kapt(Deps.Google.hiltAndroidCompiler)
    implementation(Deps.AndroidX.hiltNavigation)

    implementation(Deps.AndroidX.navigationFragment)
    implementation(Deps.AndroidX.navigationUi)

    implementation(Deps.Google.material)

    implementation(Deps.coil)

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
    implementation(Deps.Compose.paging)
    implementation(Deps.accompanistSwipeRefresh)
}