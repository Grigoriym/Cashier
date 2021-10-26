plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimAndroidPlugin)
}

android {
    buildFeatures {
        viewBinding = true
    }
}


dependencies {
    implementation(project(Modules.utilsCalculations))
    implementation(project(Modules.utilsDateTime))

    implementation(Deps.AndroidX.lifecycleLiveData)
    implementation(Deps.AndroidX.lifecycleViewModel)
    implementation(Deps.AndroidX.lifecycleRuntime)

    implementation(Deps.viewBinding)
    implementation(Deps.coil)
    implementation(Deps.recyclerViewAnimators)
    implementation(Deps.combineTuple)

    implementation(Deps.zxing) {
        isTransitive = false
    }
    implementation(Deps.Google.zxingCore)

}