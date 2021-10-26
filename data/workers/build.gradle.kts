plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimAndroidPlugin)
}

android {

}

dependencies {
    implementation(project(Modules.dataNetwork))
    implementation(project(Modules.dataDb))
    implementation(project(Modules.dataRepository))

    implementation(Deps.AndroidX.workManager)

    implementation(Deps.AndroidX.hiltWork)
    kapt(Deps.AndroidX.hiltCompiler)
}