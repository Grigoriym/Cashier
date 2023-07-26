plugins {
    id(Plugins.androidLibrary)
    id(Plugins.presentationPlugin)
}

android {
    buildFeatures {
        buildConfig = true
        compose = true
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.AndroidX.Compose.core
    }
    namespace = "com.grappim.cashbox"
}

dependencies {
    implementation(project(Modules.utilsCalculations))
    implementation(project(Modules.utilsDateTime))

    implementation(project(Modules.featureSelectInfoNavigation))

    coreLibraryDesugaring(Deps.desugar)

    implementation(Deps.AndroidX.lifecycleLiveData)
    implementation(Deps.AndroidX.lifecycleViewModel)
    implementation(Deps.AndroidX.lifecycleRuntime)

    implementation(Deps.coil)

    implementation(Deps.Compose.paging)
    implementation(Deps.Accompanist.accompanistSwipeRefresh)

    testImplementation(Deps.Mockk.core)
    testImplementation(Deps.kluent)
    testImplementation(Deps.turbine)
    testImplementation(Deps.Testing.androidCoreTesting)
    testImplementation(project(Modules.testSharedAndroid))
}