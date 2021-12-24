plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimAndroidLibraryPlugin)
}

dependencies {
    implementation(project(Modules.uikit))
    implementation(project(Modules.utilsExtensions))
    implementation(project(Modules.navigation))
    implementation(project(Modules.di))
    implementation(project(Modules.dataWorkers))

    implementation(Deps.AndroidX.lifecycleLiveData)

    implementation(Deps.Google.dagger)
    kapt(Deps.Google.daggerCompiler)

    implementation(Deps.AndroidX.navigationFragment)
    implementation(Deps.AndroidX.navigationUi)
}