plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimDataPlugin)
    id(Plugins.kotlinParcelize)
}

android {

}

dependencies {
    implementation(project(Modules.commonDi))
    implementation(project(Modules.commonDb))

    implementation(project(Modules.featureBagDomain))

    implementation(Deps.Kotlin.coroutinesCore)

    api(Deps.AndroidX.roomCore)
    kapt(Deps.AndroidX.roomCompiler)
}