plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimDataPlugin)
}

android {
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
}

dependencies {
    coreLibraryDesugaring(Deps.desugar)

    implementation(project(Modules.commonDi))
    implementation(project(Modules.commonLce))
    implementation(project(Modules.commonAsynchronous))

    implementation(project(Modules.utilsDateTime))

    implementation(project(Modules.featureBagNetwork))
    implementation(project(Modules.featureBagDomain))
    implementation(project(Modules.featureBagDb))
}