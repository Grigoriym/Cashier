plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimDataPlugin)
    id(Plugins.kotlinSerialization)
}

android {

    namespace = "com.grappim.feature.auth.data_network"
}

dependencies {
    implementation(project(Modules.commonDi))
    implementation(Deps.Kotlin.serialization)
    api(Deps.retrofit)
}