plugins {
    id(Plugins.androidLibrary)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinKapt)
    id(Plugins.safeArgs)
    id(Plugins.hiltAndroid)
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
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.AndroidX.Compose.core
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = ConfigData.kotlinJvmTarget
    }
    val compilerArgs = listOf(
        "-Xuse-experimental=androidx.compose.ui.ExperimentalComposeUiApi",
        "-Xuse-experimental=androidx.compose.material.ExperimentalMaterialApi"
    )
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions.freeCompilerArgs = compilerArgs
    }
}

dependencies {
    implementation(project(Modules.domain))
    implementation(project(Modules.logger))
    implementation(project(Modules.uikit))
    implementation(project(Modules.utilsExtensions))
    implementation(project(Modules.dataWorkers))

    implementation(Deps.Kotlin.coroutinesCore)
    implementation(Deps.Kotlin.coroutinesAndroid)

    implementation(Deps.Google.hilt)
    kapt(Deps.Google.hiltAndroidCompiler)

    implementation(Deps.AndroidX.core)
    implementation(Deps.AndroidX.appCompat)

    implementation(Deps.AndroidX.navigationFragment)
    implementation(Deps.AndroidX.navigationRuntime)
    implementation(Deps.AndroidX.navigationUi)

    implementation(Deps.Google.material)

    implementation(Deps.Compose.ui)
    implementation(Deps.Compose.material)
    implementation(Deps.Compose.toolingPreview)
    implementation(Deps.Compose.uiTooling)
    implementation(Deps.Compose.runtime)
    implementation(Deps.Compose.runtimeLivedata)
    implementation(Deps.Compose.foundation)
    implementation(Deps.Compose.foundationLayout)
    implementation(Deps.Compose.icons)
    implementation(Deps.Compose.constraint)
    implementation(Deps.Compose.lifecycleViewModel)
}