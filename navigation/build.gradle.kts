plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimAndroidLibraryPlugin)
}

dependencies {
    implementation(project(Modules.uikit))
    implementation(project(Modules.commonDi))

    implementation(Deps.cicerone)
    implementation(Deps.AndroidX.fragment)

    implementation(Deps.Google.dagger)
    kapt(Deps.Google.daggerCompiler)
}
android {
    namespace = "com.grappim.navigation"
}
