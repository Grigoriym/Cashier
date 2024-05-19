import com.grappim.cashier.CashierBuildTypes

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.gms.googleServices)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.moduleGraphAssertion)

    alias(libs.plugins.cashier.android.dagger)
}

android {
    namespace = "com.grappim.cashier"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.grappim.cashier"
        testApplicationId = "com.grappim.cashier.tests"

        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    packaging.resources.excludes.apply {
        add("META-INF/AL2.0")
        add("META-INF/LGPL2.1")
        add("META-INF/LICENSE.md")
        add("META-INF/LICENSE-notice.md")
    }

    buildTypes {
        debug {
            applicationIdSuffix = CashierBuildTypes.DEBUG.applicationIdSuffix
        }
        release {
            applicationIdSuffix = CashierBuildTypes.RELEASE.applicationIdSuffix

            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17

        isCoreLibraryDesugaringEnabled = true
    }
    kotlinOptions {
        jvmTarget = "17"

        freeCompilerArgs += "-opt-in=androidx.compose.material.ExperimentalMaterialApi"
        freeCompilerArgs += "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi"
        freeCompilerArgs += "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi"
        freeCompilerArgs += "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeKotlinCompiler.get()
    }
    testOptions {
        unitTests {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true
        }
    }
    bundle {
        language {
            // Specifies that the app bundle should not support
            // configuration APKs for language resources. These
            // resources are instead packaged with each base and
            // dynamic feature APK.
            enableSplit = false
        }
    }
}

dependencies {
    implementation(project(":utils:biometric"))

    implementation(project(":feature:auth:network"))
    implementation(project(":feature:auth:presentation"))

    implementation(project(":feature:payment-method:domain"))
    implementation(project(":feature:payment-method:presentation"))

    implementation(project(":feature:product-category:presentation"))

    implementation(project(":feature:waybill:network"))
    implementation(project(":feature:waybill:domain"))
    implementation(project(":feature:waybill:repository"))
    implementation(project(":feature:waybill:presentation"))

    implementation(project(":feature:bag:presentation"))
    implementation(project(":feature:bag:domain"))
    implementation(project(":feature:bag:db"))
    implementation(project(":feature:bag:network"))
    implementation(project(":feature:bag:repository"))

    implementation(project(":feature:menu"))

    implementation(project(":feature:products:presentation"))

    implementation(project(":feature:products:domain"))
    implementation(project(":feature:products:repository"))
    implementation(project(":feature:products:network"))

    implementation(project(":feature:select-info:root-presentation"))

    implementation(project(":feature:settings"))

    implementation(project(":feature:sales"))
    implementation(project(":feature:scanner"))

    implementation(project(":feature:sign-up:presentation"))

    implementation(project(":common:di"))
    implementation(project(":domain"))
    implementation(project(":core"))
    implementation(project(":navigation"))
    implementation(project(":utils:logger"))

    coreLibraryDesugaring(libs.android.desugarJdkLibs)

    implementation(libs.androidx.work.runtime)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.analytics)

    implementation(libs.cicerone)
    implementation(libs.androidx.splashscreen)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.appcompat)
}

moduleGraphAssert {
    maxHeight = 10
    assertOnAnyBuild = true
}
