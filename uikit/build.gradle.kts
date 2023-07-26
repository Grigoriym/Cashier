plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimAndroidLibraryPlugin)
}

android {
    buildFeatures {
        buildConfig = true
        viewBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.AndroidX.Compose.core
    }
    namespace = "com.grappim.uikit"
}

dependencies {
    implementation(project(Modules.domain))
    implementation(project(Modules.utilsCalculations))
    implementation(project(Modules.featureWaybillDomain))

    implementation(Deps.Google.material)

    implementation(Deps.zxing) {
        isTransitive = false
    }
    implementation(Deps.Google.zxingCore)

    implementation(Deps.AndroidX.splashScreen)
    implementation(Deps.AndroidX.swipeRefresh)
    implementation(Deps.circularProgressBar)
    implementation(Deps.viewBinding)

    implementation(platform(Deps.Compose.bom))
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

    implementation(Deps.Accompanist.systemUiController())
}