plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimDataPlugin)
    id(Plugins.kotlinSerialization)
}

android {
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
    namespace = "com.grappim.products.network"
}

dependencies {
    implementation(project(Modules.dataDb))
    implementation(project(Modules.utilsDateTime))

    implementation(project(Modules.commonNetworkSerializers))
    implementation(project(Modules.commonDi))
    implementation(project(Modules.commonAnnotations))
    implementation(project(Modules.commonAsynchronous))

    implementation(project(Modules.featureBagDomain))
    implementation(project(Modules.featureBagNetwork))
    implementation(project(Modules.featureBagDb))

    implementation(Deps.Kotlin.serialization)

    api(Deps.retrofit)

    implementation(Deps.AndroidX.pagingCommon)

    coreLibraryDesugaring(Deps.desugar)
}