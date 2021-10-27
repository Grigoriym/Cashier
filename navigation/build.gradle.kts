plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimAndroidPlugin)
    id(Plugins.safeArgs)
}

dependencies {
    implementation(project(Modules.uikit))

    implementation(Deps.AndroidX.navigationFragment)
    implementation(Deps.AndroidX.navigationUi)

    implementation(Deps.Google.hilt)
    kapt(Deps.Google.hiltAndroidCompiler)
}