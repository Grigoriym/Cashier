plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimAndroidLibraryPlugin)
}

android {
    buildFeatures {
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
    implementation(project(Modules.utilsCalculations))
    implementation(project(Modules.utilsDateTime))

    implementation(project(Modules.commonDi))
    implementation(project(Modules.commonLce))
    implementation(project(Modules.commonAsynchronous))
    implementation(project(Modules.featureSelectInfoNavigation))

    coreLibraryDesugaring(Deps.desugar)

    implementation(Deps.AndroidX.lifecycleLiveData)
    implementation(Deps.AndroidX.lifecycleViewModel)
    implementation(Deps.AndroidX.lifecycleRuntime)

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