plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimAndroidLibraryPlugin)
}

dependencies {
    implementation(Deps.AndroidX.lifecycleLiveData)
}