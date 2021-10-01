plugins {
    id(Plugins.androidLibrary)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinKapt)
}

android {
    compileSdk = ConfigData.compileSdk

    defaultConfig {
        minSdk = ConfigData.minSdk
        targetSdk = ConfigData.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = ConfigData.kotlinJvmTarget
    }
}

dependencies {
    implementation(project(Modules.domain))
    implementation(project(Modules.dataNetwork))
    implementation(project(Modules.dataDb))
    implementation(project(Modules.dataRepository))
    implementation(project(Modules.logger))

    implementation(Deps.Kotlin.coroutinesCore)
    implementation(Deps.Kotlin.coroutinesAndroid)

    implementation(Deps.AndroidX.workManager)
    implementation(Deps.Google.hilt)
    kapt(Deps.Google.hiltAndroidCompiler)

    implementation(Deps.AndroidX.hiltWork)
    kapt(Deps.AndroidX.hiltCompiler)
}