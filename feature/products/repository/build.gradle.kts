plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimDataPlugin)
    id(Plugins.protobuf) version Versions.protobufPlugin
}

android {
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
    namespace = "com.grappim.feature.products.repository"
}


dependencies {
    implementation(project(Modules.dataNetwork))
    implementation(project(Modules.dataDb))
    implementation(project(Modules.utilsDateTime))

    implementation(project(Modules.commonDi))
    implementation(project(Modules.commonDb))
    implementation(project(Modules.commonLce))
    implementation(project(Modules.commonAsynchronous))

    implementation(project(Modules.featureProductsDomain))
    implementation(project(Modules.featureProductsNetwork))
    implementation(project(Modules.featureProductCategoryDomain))

    implementation(project(Modules.featureBagNetwork))
    implementation(project(Modules.featureBagDb))

    implementation(project(Modules.domain))

    implementation(Deps.AndroidX.paging)

    coreLibraryDesugaring(Deps.desugar)
}