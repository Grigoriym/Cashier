plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimAndroidLibraryPlugin)
}

android {

    namespace = "com.grappim.extensions"
}

dependencies {
    implementation(project(Modules.uikit))
    implementation(project(Modules.domain))

    implementation(Deps.AndroidX.fragment)

    implementation(Deps.AndroidX.lifecycleViewModel)
    implementation(Deps.AndroidX.lifecycleRuntime)

    implementation(Deps.coil)
}