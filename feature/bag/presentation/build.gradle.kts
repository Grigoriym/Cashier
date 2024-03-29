plugins {
    id(Plugins.androidLibrary)
    id(Plugins.presentationPlugin)
}

android {
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.AndroidX.Compose.core
    }
    namespace = "com.grappim.feature.bag.presentation"
}

dependencies {
    implementation(project(Modules.utilsCalculations))
    implementation(project(Modules.utilsDateTime))

    implementation(project(Modules.featureBagDomain))
    implementation(project(Modules.featureBagNetwork))
    implementation(project(Modules.featureBagRepository))

    implementation(Deps.AndroidX.lifecycleLiveData)
    implementation(Deps.AndroidX.lifecycleViewModel)
    implementation(Deps.AndroidX.lifecycleRuntime)
}