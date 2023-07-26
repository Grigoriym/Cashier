plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimDataPlugin)
}

android {

    namespace = "com.grappim.feature.auth.data_repository"
}

dependencies {
    implementation(project(Modules.commonDi))
    implementation(project(Modules.commonDb))
    implementation(project(Modules.commonLce))
    implementation(project(Modules.commonAsynchronous))
    implementation(project(Modules.dataRepository))

    implementation(project(Modules.featureAuthDomain))
    implementation(project(Modules.featureAuthNetwork))
}