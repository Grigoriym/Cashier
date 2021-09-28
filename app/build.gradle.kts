plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
    id("io.gitlab.arturbosch.detekt")
}

android {
    compileSdk = ConfigData.compileSdk

    defaultConfig {
        applicationId = "com.grappim.cashier"
        minSdk = ConfigData.minSdk
        targetSdk = ConfigData.targetSdk
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf("room.schemaLocation" to "$projectDir/schemas")
            }
        }
    }

    lint {
        isAbortOnError = false
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }

    kapt {
        correctErrorTypes = true
    }

    hilt {
        enableAggregatingTask = true
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
        }
        getByName("release") {
            isMinifyEnabled = true
            isDebuggable = false

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

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.AndroidX.Compose.core
    }

    kotlinOptions {
        jvmTarget = ConfigData.kotlinJvmTarget
    }

    val compilerArgs = listOf(
        "-Xuse-experimental=androidx.compose.ui.ExperimentalComposeUiApi",
        "-Xuse-experimental=androidx.compose.material.ExperimentalMaterialApi",
        "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi"
    )
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions.freeCompilerArgs = compilerArgs
    }
}

dependencies {
    implementation(project(Modules.domain))

    implementation(project(Modules.data_network))
    implementation(project(Modules.data_db))
    implementation(project(Modules.data_repository))

    implementation(project(Modules.logger))

    implementation(project(Modules.utils_calculations))
    implementation(project(Modules.utils_date_time))

    implementation(Deps.Kotlin.coroutinesCore)
    implementation(Deps.Kotlin.coroutinesAndroid)

    implementation(Deps.AndroidX.core)
    implementation(Deps.AndroidX.appCompat)
    implementation(Deps.AndroidX.constraintLayout)
    implementation(Deps.AndroidX.viewPager2)
    implementation(Deps.AndroidX.swipeRefresh)
    implementation(Deps.AndroidX.paging)
    implementation(Deps.AndroidX.workManager)
    implementation(Deps.AndroidX.startup)

    implementation(Deps.AndroidX.navigationFragment)
    implementation(Deps.AndroidX.navigationRuntime)
    implementation(Deps.AndroidX.navigationUi)

    implementation(Deps.AndroidX.lifecycleLiveData)
    implementation(Deps.AndroidX.lifecycleViewModel)
    implementation(Deps.AndroidX.lifecycleRuntime)

    implementation(Deps.Compose.ui)
    implementation(Deps.Compose.material)
    implementation(Deps.Compose.toolingPreview)
    implementation(Deps.Compose.uiTooling)
    implementation(Deps.Compose.runtime)
    implementation(Deps.Compose.runtimeLivedata)
    implementation(Deps.Compose.foundation)
    implementation(Deps.Compose.foundationLayout)
    implementation(Deps.Compose.icons)
    implementation(Deps.Compose.lifecycleViewModel)
    implementation(Deps.Compose.paging)
    implementation(Deps.Compose.constraint)
    implementation(Deps.Compose.hiltNavigation)
    implementation(Deps.Compose.navigation)

    implementation(Deps.accompanistSwipeRefresh)

    implementation(Deps.Google.hilt)
    kapt(Deps.Google.hiltAndroidCompiler)

    implementation(Deps.AndroidX.hiltNavigation)
    implementation(Deps.AndroidX.hiltWork)
    kapt(Deps.AndroidX.hiltCompiler)

    implementation(Deps.Google.material)

    implementation(Deps.zxing) {
        isTransitive = false
    }
    implementation(Deps.Google.zxingCore)
    implementation(Deps.combineTuple)
    implementation(Deps.recyclerViewAnimators)
    implementation(Deps.circularProgressBar)
    implementation(Deps.viewBinding)
    implementation(Deps.coil)
    implementation(Deps.coilCompose)

    coreLibraryDesugaring(Deps.desugar)

    debugImplementation(Deps.debugDb)
}