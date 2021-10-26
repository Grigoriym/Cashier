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
    implementation(project(Modules.dataNetwork))
    implementation(project(Modules.dataDb))
    implementation(project(Modules.utilsCalculations))
    implementation(project(Modules.utilsDateTime))

    implementation(Deps.AndroidX.paging)

    coreLibraryDesugaring(Deps.desugar)
}