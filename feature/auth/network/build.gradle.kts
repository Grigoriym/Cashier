plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimDataPlugin)
    id(Plugins.kotlinSerialization)
}

android {

}

dependencies {
    implementation(project(Modules.commonDi))
    implementation(Deps.Kotlin.serialization)
    api(Deps.retrofit)
}