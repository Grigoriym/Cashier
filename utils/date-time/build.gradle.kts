plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimAndroidLibraryPlugin)
}
android {
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
}

dependencies {
    implementation(project(Modules.commonDi))

    implementation(Deps.Kotlin.time)

    implementation(Deps.Google.dagger)
    kapt(Deps.Google.daggerCompiler)

    coreLibraryDesugaring(Deps.desugar)
}