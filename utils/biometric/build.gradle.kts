plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimAndroidLibraryPlugin)
    id(Plugins.kotlinParcelize)
}

android {

}

dependencies {
    implementation(project(Modules.commonDi))
    implementation(project(Modules.uikit))

    api(Deps.AndroidX.biometricKotlin)
    implementation(Deps.Google.dagger)
    implementation(Deps.Google.daggerCompiler)
    implementation(Deps.AndroidX.appCompat)
}