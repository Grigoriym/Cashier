plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimAndroidLibraryPlugin)
    id(Plugins.hiltAndroid)
}

dependencies {
    implementation(project(Modules.uikit))
    implementation(project(Modules.utilsExtensions))
    implementation(project(Modules.navigation))
    implementation(project(Modules.dataWorkers))

    implementation(Deps.Google.hilt)
    kapt(Deps.Google.hiltAndroidCompiler)

    implementation(Deps.AndroidX.navigationFragment)
    implementation(Deps.AndroidX.navigationUi)
}