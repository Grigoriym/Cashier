plugins {
    id(Plugins.androidApplication)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinKapt)
    id(Plugins.hiltAndroid)
    id(Plugins.safeArgs)
    id(Plugins.detekt)
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

        javacOptions {
            option("-Xmaxerrs", 500)
        }
    }

    hilt {
        enableAggregatingTask = true
    }

    buildTypes {
        getByName(BuildType.DEBUG) {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            isDebuggable = BuildTypeDebug.isDebuggable
            isTestCoverageEnabled = BuildTypeDebug.isTestCoverageEnabled
        }

        getByName(BuildType.RELEASE) {
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            isDebuggable = BuildTypeRelease.isDebuggable
            isTestCoverageEnabled = BuildTypeRelease.isTestCoverageEnabled

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions.add(ConfigData.FLAVOR_ENVIRONMENT)
    productFlavors {
        create(ProductFlavor.DEV) {
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            dimension = ConfigData.FLAVOR_ENVIRONMENT
        }
        create(ProductFlavor.QA) {
            applicationIdSuffix = ".qa"
            versionNameSuffix = "-qa"
            dimension = ConfigData.FLAVOR_ENVIRONMENT
        }
        create(ProductFlavor.PROD) {
            applicationIdSuffix = ".prod"
            versionNameSuffix = "-prod"
            dimension = ConfigData.FLAVOR_ENVIRONMENT
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

    implementation(project(Modules.dataNetwork))
    implementation(project(Modules.dataDb))
    implementation(project(Modules.dataRepository))
    implementation(project(Modules.dataWorkers))

    implementation(project(Modules.logger))
    implementation(project(Modules.uikit))

    implementation(project(Modules.featureAuth))
    implementation(project(Modules.featureWaybill))

    implementation(project(Modules.utilsCalculations))
    implementation(project(Modules.utilsDateTime))
    implementation(project(Modules.utilsExtensions))

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