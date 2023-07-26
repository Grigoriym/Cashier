plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimAndroidLibraryPlugin)
}

dependencies {
    implementation(Deps.AndroidX.lifecycleLiveData)
}
android {
    namespace = "com.grappim.test_shared_android"
}
