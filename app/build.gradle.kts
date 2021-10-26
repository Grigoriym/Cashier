import commons.buildTypes.BuildTypeDebug
import commons.buildTypes.BuildTypeRelease
import commons.flavors.ProductFlavorDev
import commons.flavors.ProductFlavorProd
import commons.flavors.ProductFlavorQa

plugins {
    id(Plugins.androidApplication)
    id(Plugins.grappimAndroidPlugin)
    id(Plugins.detekt)
}

android {
    defaultConfig {
        applicationId = "com.grappim.cashier"
        versionCode = 1
        versionName = "1.0.0"
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

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.AndroidX.Compose.core
    }

    buildTypes {
        BuildTypeDebug.appCreate(this)
        BuildTypeRelease.appCreate(
            namedDomainObjectContainer = this,
            proguardFile = getDefaultProguardFile("proguard-android-optimize.txt")
        )
    }
    flavorDimensions.add(ConfigData.FLAVOR_ENVIRONMENT)
    productFlavors {
        ProductFlavorDev.appCreate(this)
        ProductFlavorQa.appCreate(this)
        ProductFlavorProd.appCreate(this)
    }
    variantFilter {
        val flavorNames = flavors.map { it.name }
        if (buildType.name == BuildTypeRelease.name && flavorNames.contains(ProductFlavorDev.name)) {
            ignore = true
        }
        if (buildType.name == BuildTypeRelease.name && flavorNames.contains(ProductFlavorQa.name)) {
            ignore = true
        }
    }
}

dependencies {
    implementation(project(Modules.dataNetwork))
    implementation(project(Modules.dataDb))
    implementation(project(Modules.dataRepository))
    implementation(project(Modules.dataWorkers))

    implementation(project(Modules.utilsCalculations))
    implementation(project(Modules.utilsDateTime))

    implementation(Deps.AndroidX.constraintLayout)
    implementation(Deps.AndroidX.viewPager2)
    implementation(Deps.AndroidX.swipeRefresh)
    implementation(Deps.AndroidX.paging)
    implementation(Deps.AndroidX.workManager)
    implementation(Deps.AndroidX.startup)

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

    implementation(Deps.AndroidX.hiltNavigation)
    implementation(Deps.AndroidX.hiltWork)
    kapt(Deps.AndroidX.hiltCompiler)

    implementation(Deps.zxing) {
        isTransitive = false
    }
    implementation(Deps.Google.zxingCore)
    implementation(Deps.combineTuple)
    implementation(Deps.recyclerViewAnimators)
    implementation(Deps.viewBinding)
    implementation(Deps.coil)
    implementation(Deps.coilCompose)

    coreLibraryDesugaring(Deps.desugar)

    debugImplementation(Deps.debugDb)
}