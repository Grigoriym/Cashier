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
import org.jetbrains.kotlin.gradle.plugin.KaptAnnotationProcessorOptions

internal class AndroidAppModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        println("gradlePlugin info: $target | ${target.properties["android"]}")

        if (target.hasProperty("android")) {
            with(target) {
                configurePlugins()
                configureAndroidBlock()
                configureBuildVariants()
                getCommonVariantFilters()
                configureCommonDependencies()
                configureCommonTestDependencies()
            }
        }
    }
}

private fun Project.configurePlugins() =
    extensions.getByType<BaseAppModuleExtension>().run {
        getCommonPlugins()
        plugins.apply(Plugins.safeArgs)

        plugins.apply(Plugins.googleServices)
        plugins.apply(Plugins.detekt)

        turnOffGoogleServicesOnDebugBuilds()
    }

private fun Project.turnOffGoogleServicesOnDebugBuilds() =
    extensions.getByType<BaseAppModuleExtension>().run {
        applicationVariants.all {
            println("asd ${this.name}")
            if (this.name.contains("qa") ||
                this.name.contains("dev")
            ) {
                tasks.all {
                    println("asd tasks: ${this.name}")
                    if (this.name.contains("GoogleServices")) {
                        this.enabled = false
                    }
                }
            }
        }
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

private fun KaptAnnotationProcessorOptions.daggerSettings() {
    arg("dagger.formatGeneratedSource", "enabled")
    arg("dagger.fullBindingGraphValidation", "ERROR")
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
            arguments {
                daggerSettings()
            }
        }

        lint {
            isAbortOnError = false
            isCheckReleaseBuilds = false
            isIgnoreTestSources = true
            isWarningsAsErrors = true
            xmlReport = false
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

            implementation(project(Modules.commonDi))
            implementation(project(Modules.commonDb))
            implementation(project(Modules.commonLce))
            implementation(project(Modules.commonAsynchronous))

            implementation(project(Modules.featureAuth))
            implementation(project(Modules.featureWaybill))
            implementation(project(Modules.featureBag))
            implementation(project(Modules.featurePaymentMethod))
            implementation(project(Modules.featureSales))
            implementation(project(Modules.featureMenu))
            implementation(project(Modules.featureProducts))
            implementation(project(Modules.featureScanner))

            implementation(project(Modules.featureSignUpPresentation))
            implementation(project(Modules.featureSignUpDomain))
            implementation(project(Modules.featureSignUpRepository))

            implementation(project(Modules.featureSelectInfoRootPresentation))
            implementation(project(Modules.selectInfoSelectStock))
            implementation(project(Modules.selectInfoSelectCashbox))
            implementation(project(Modules.featureSelectInfoNavigation))

            implementation(project(Modules.featureProductCategoryPresentation))
            implementation(project(Modules.featureProductCategoryDomain))
            implementation(project(Modules.featureProductCategoryNetwork))
            implementation(project(Modules.featureProductCategoryDb))
            implementation(project(Modules.featureProductCategoryRepository))

            implementation(project(Modules.utilsCalculations))
            implementation(project(Modules.utilsDateTime))

            implementation(platform(Deps.Firebase.bom))
            implementation(Deps.Firebase.analytics)

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
            implementation(Deps.Compose.navigation)

            implementation(Deps.accompanistSwipeRefresh)

            implementation(Deps.Google.zxingCore)
            implementation(Deps.combineTupleLiveData)
            implementation(Deps.combineTupleFlow)
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
