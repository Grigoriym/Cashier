package commons.plugins

import ConfigData
import Deps
import Modules
import Plugins
import Versions
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import commons.buildTypes.BuildTypeDebug
import commons.buildTypes.BuildTypeRelease
import commons.flavors.ProductFlavorDev
import commons.flavors.ProductFlavorProd
import commons.flavors.ProductFlavorQa
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.project

internal class AndroidAppModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        println("gradlePlugin info: $target | ${target.properties["android"]}")

        if (target.hasProperty("android")) {
            with(target) {
                configurePlugins()
                configureAndroidBlock()
                configureBuildVariants()
                configureCommonDependencies()
                configureCommonTestDependencies()
            }
        }
    }
}

private fun Project.configurePlugins() =
    extensions.getByType<BaseAppModuleExtension>().run {
        getCommonPlugins()
        plugins.apply(Plugins.hiltAndroid)
        plugins.apply(Plugins.safeArgs)
    }

private fun Project.configureAndroidBlock() =
    extensions.getByType<BaseAppModuleExtension>().run {
        configureAppAndroidBlock()
        getCommonAndroidBlock()
    }

private fun Project.configureBuildVariants() =
    extensions.getByType<BaseAppModuleExtension>().run {
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

private fun Project.configureCommonDependencies() {
    extensions.getByType<BaseAppModuleExtension>().run {
        dependencies {
            println("module name: $name, $displayName")
            getCommonDependencies()
            getCommonAndroidDependencies()
            getAppDependencies()
        }
    }
}

private fun Project.configureAppAndroidBlock() =
    extensions.getByType<BaseAppModuleExtension>().run {
        defaultConfig {
            applicationId = "com.grappim.cashier"
            versionCode = 1
            versionName = "1.0.0"
        }

        kapt {
            correctErrorTypes = true

            javacOptions {
                option("-Xmaxerrs", 500)
            }
        }

        lint {
            isAbortOnError = false
        }

        buildFeatures {
            viewBinding = true
            compose = true
        }

        compileOptions {
            isCoreLibraryDesugaringEnabled = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = Versions.AndroidX.Compose.core
        }
    }

private fun Project.configureCommonTestDependencies() {
    extensions.getByType<BaseAppModuleExtension>().run {
        getCommonTestDependencies()
    }
}

private fun Project.getAppDependencies() {
    extensions.getByType<BaseAppModuleExtension>().run {
        dependencies {
            implementation(project(Modules.dataNetwork))
            implementation(project(Modules.dataDb))
            implementation(project(Modules.dataRepository))
            implementation(project(Modules.dataWorkers))

            implementation(project(Modules.featureAuth))
            implementation(project(Modules.featureWaybill))
            implementation(project(Modules.featureSelectCashBox))
            implementation(project(Modules.featureSelectStock))
            implementation(project(Modules.featureBag))
            implementation(project(Modules.featurePaymentMethod))
            implementation(project(Modules.featureSales))
            implementation(project(Modules.featureMenu))
            implementation(project(Modules.featureProducts))
            implementation(project(Modules.featureScanner))
            implementation(project(Modules.featureSignUp))
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

            implementation(Deps.Google.zxingCore)
            implementation(Deps.combineTuple)
            implementation(Deps.recyclerViewAnimators)
            implementation(Deps.viewBinding)
            implementation(Deps.coil)
            implementation(Deps.coilCompose)

            implementation(Deps.zxing) {
                isTransitive = false
            }

            coreLibraryDesugaring(Deps.desugar)
            debugImplementation(Deps.debugDb)
        }
    }
}
