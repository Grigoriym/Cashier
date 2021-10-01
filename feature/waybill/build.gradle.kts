plugins {
    id(Plugins.androidLibrary)
    kotlin(Plugins.kotlinAndroid)
}

android {
    compileSdk = ConfigData.compileSdk

    defaultConfig {
        minSdk = ConfigData.minSdk
        targetSdk = ConfigData.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.AndroidX.Compose.core
    }
    kotlinOptions {
        jvmTarget = ConfigData.kotlinJvmTarget
    }
}

dependencies {
    implementation(project(Modules.domain))
    implementation(project(Modules.logger))
    implementation(project(Modules.uikit))

    implementation(Deps.Kotlin.coroutinesCore)
    implementation(Deps.Kotlin.coroutinesAndroid)

    implementation(Deps.AndroidX.core)
    implementation(Deps.AndroidX.appCompat)
}