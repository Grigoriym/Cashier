plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimDataPlugin)
}

dependencies {
    implementation(project(Modules.dataNetwork))
    implementation(project(Modules.dataDb))
    implementation(project(Modules.dataRepository))

    implementation(project(Modules.commonDi))
    implementation(project(Modules.commonDb))
    implementation(project(Modules.commonAsynchronous))

    implementation(project(Modules.featureProductCategoryDb))
    implementation(project(Modules.featureProductCategoryDomain))

    implementation(Deps.AndroidX.workManager)
}