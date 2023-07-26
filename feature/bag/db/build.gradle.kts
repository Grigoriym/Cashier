plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimDataPlugin)
    id(Plugins.kotlinParcelize)
}

android {

    namespace = "com.grappim.feature.bag.db"
}

dependencies {
    implementation(project(Modules.commonDi))
    implementation(project(Modules.commonDb))

    implementation(project(Modules.featureBagDomain))

    implementation(Deps.Kotlin.coroutinesCore)

    api(Deps.AndroidX.roomCore)
    kapt(Deps.AndroidX.roomCompiler)
}