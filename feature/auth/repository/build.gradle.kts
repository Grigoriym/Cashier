plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimDataPlugin)
}

android {

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