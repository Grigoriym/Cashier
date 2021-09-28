plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
}

android {
    compileSdk = ConfigData.compileSdk

    defaultConfig {
        minSdk = ConfigData.minSdk
        targetSdk = ConfigData.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(
            "String",
            "CASHIER_API",
            "\"https://quiet-shore-01215.herokuapp.com/\""
        )
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = ConfigData.kotlinJvmTarget
    }

    val compilerArgs = listOf(
        "-Xuse-experimental=kotlinx.serialization.ExperimentalSerializationApi",
    )
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions.freeCompilerArgs = compilerArgs
    }
}

dependencies {
    implementation(project(Modules.domain))
    implementation(project(Modules.logger))
    implementation(project(Modules.utils_calculations))

    implementation(Deps.Kotlin.coroutinesCore)
    implementation(Deps.Kotlin.coroutinesAndroid)
    implementation(Deps.Kotlin.serialization)

    api(Deps.AndroidX.roomCore)
    kapt(Deps.AndroidX.roomCompiler)

    implementation(Deps.Google.hilt)
    kapt(Deps.Google.hiltAndroidCompiler)
}