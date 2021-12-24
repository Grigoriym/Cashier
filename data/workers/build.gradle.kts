plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimDataPlugin)
}

dependencies {
    implementation(project(Modules.dataNetwork))
    implementation(project(Modules.dataDb))
    implementation(project(Modules.dataRepository))
    implementation(project(Modules.di))

    implementation(Deps.AndroidX.workManager)
}