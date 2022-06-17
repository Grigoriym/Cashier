plugins {
    id(Plugins.androidLibrary)
    id(Plugins.presentationPlugin)
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
    implementation(project(Modules.utilsCalculations))
    implementation(project(Modules.utilsDateTime))

    implementation(project(Modules.featureWaybillDomain))
    implementation(project(Modules.featureWaybillNetwork))
    implementation(project(Modules.featureWaybillRepository))

    implementation(project(Modules.featureProductsDomain))

    coreLibraryDesugaring(Deps.desugar)

    implementation(Deps.AndroidX.lifecycleLiveData)
    implementation(Deps.AndroidX.lifecycleViewModel)
    implementation(Deps.AndroidX.lifecycleRuntime)

    implementation(Deps.zxing) {
        isTransitive = false
    }
    implementation(Deps.Google.zxingCore)

    implementation(Deps.viewBinding)

    implementation(Deps.coil)

    implementation(Deps.Compose.paging)
    implementation(Deps.Accompanist.accompanistSwipeRefresh)
}