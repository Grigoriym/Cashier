plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimAndroidLibraryPlugin)
    id(Plugins.safeArgs)
}

dependencies {
    implementation(project(Modules.uikit))
    implementation(project(Modules.commonDi))

    implementation(Deps.cicerone)

    implementation(Deps.AndroidX.navigationFragment)
    implementation(Deps.AndroidX.navigationUi)

    implementation(Deps.Google.dagger)
    kapt(Deps.Google.daggerCompiler)
}