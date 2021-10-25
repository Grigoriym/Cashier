plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimAndroidPlugin)
    id(Plugins.hiltAndroid)
}

android {
    defaultConfig {
        buildConfigField(
            "String",
            "CASHIER_API",
            "\"https://quiet-shore-01215.herokuapp.com/\""
        )
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
}

dependencies {
    implementation(project(Modules.domain))
    implementation(project(Modules.logger))
    implementation(project(Modules.dataDb))
    implementation(project(Modules.utilsDateTime))

    implementation(Deps.Google.hilt)
    kapt(Deps.Google.hiltAndroidCompiler)

    implementation(Deps.Google.gson)

    implementation(Deps.okhttp)
    implementation(Deps.loggingInterceptor)
    implementation(Deps.retrofit)
    implementation(Deps.retrofitGsonConverter)

    debugImplementation(Deps.chucker)
    releaseImplementation(Deps.chuckerNoOp)

    coreLibraryDesugaring(Deps.desugar)
}