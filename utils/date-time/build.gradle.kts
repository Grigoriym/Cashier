plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimAndroidPlugin)
}
android {
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
}

dependencies {
    implementation(Deps.Kotlin.time)

    implementation(Deps.Google.hilt)
    kapt(Deps.Google.hiltAndroidCompiler)

    coreLibraryDesugaring(Deps.desugar)
}