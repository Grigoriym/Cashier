plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimAndroidPlugin)
    id(Plugins.hiltAndroid)
    id(Plugins.safeArgs)
}

android {
    buildFeatures {
        viewBinding = true
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

    implementation(Deps.AndroidX.lifecycleLiveData)
    implementation(Deps.AndroidX.lifecycleViewModel)
    implementation(Deps.AndroidX.lifecycleRuntime)

    implementation(Deps.Google.hilt)
    kapt(Deps.Google.hiltAndroidCompiler)

    implementation(Deps.AndroidX.navigationFragment)
    implementation(Deps.AndroidX.navigationUi)

    implementation(Deps.Google.material)
    implementation(Deps.viewBinding)
    implementation(Deps.coil)
}