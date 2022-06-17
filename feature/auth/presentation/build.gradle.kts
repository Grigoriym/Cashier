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

dependencies {
    implementation(project(Modules.dataWorkers))
    implementation(project(Modules.utilsBiometric))

    implementation(project(Modules.featureAuthDomain))
    implementation(project(Modules.featureAuthRepository))
    implementation(project(Modules.featureAuthNetwork))
    implementation(project(Modules.dataRepository))

    testImplementation(Deps.Mockk.core)
    testImplementation(Deps.kluent)
    testImplementation(Deps.turbine)
    testImplementation(Deps.Testing.androidCoreTesting)
    testImplementation(project(Modules.testSharedAndroid))
}