plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = ConfigData.compileSdk

    defaultConfig {
        minSdk = ConfigData.minSdk
        targetSdk = ConfigData.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = true

            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true

        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = ConfigData.kotlinJvmTarget
    }
}

dependencies {
    implementation(project(Modules.domain))
    implementation(project(Modules.logger))
    implementation(project(Modules.data_network))
    implementation(project(Modules.data_db))
    implementation(project(Modules.utils_calculations))
    implementation(project(Modules.utils_date_time))

    implementation(Deps.Kotlin.coroutinesCore)
    implementation(Deps.Kotlin.coroutinesAndroid)

    implementation(Deps.Google.hilt)
    kapt(Deps.Google.hiltAndroidCompiler)

    implementation(Deps.AndroidX.paging)

    coreLibraryDesugaring(Deps.desugar)
}