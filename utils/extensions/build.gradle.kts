plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimAndroidPlugin)
}

android {

}

dependencies {
    implementation(project(Modules.logger))
    implementation(project(Modules.uikit))
    implementation(project(Modules.domain))

    implementation(Deps.AndroidX.fragment)

    implementation(Deps.AndroidX.lifecycleViewModel)
    implementation(Deps.AndroidX.lifecycleRuntime)

    implementation(Deps.coil)
}