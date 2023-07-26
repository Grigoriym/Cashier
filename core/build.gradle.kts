plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimAndroidLibraryPlugin)
}

dependencies {
    implementation(project(Modules.uikit))
    implementation(project(Modules.utilsExtensions))
    implementation(project(Modules.navigation))
    implementation(project(Modules.commonDi))
    implementation(project(Modules.dataWorkers))
    implementation(project(Modules.domain))

    implementation(Deps.AndroidX.lifecycleLiveData)
    implementation(Deps.cicerone)
    implementation(Deps.AndroidX.fragment)
    implementation(Deps.AndroidX.appCompat)

    implementation(Deps.Google.dagger)
    kapt(Deps.Google.daggerCompiler)
}
android {
    namespace = "com.grappim.core"
}
