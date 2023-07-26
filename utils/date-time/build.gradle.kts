plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimAndroidLibraryPlugin)
}
android {
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
    namespace = "com.grappim.date_time"
}

dependencies {
    implementation(project(Modules.commonDi))

    implementation(Deps.Kotlin.time)

    implementation(Deps.Google.dagger)
    kapt(Deps.Google.daggerCompiler)

    coreLibraryDesugaring(Deps.desugar)
}