package commons

import ConfigData
import Deps
import com.android.build.gradle.BaseExtension
import commons.buildTypes.BuildTypeDebug
import commons.buildTypes.BuildTypeRelease
import commons.flavors.ProductFlavorDev
import commons.flavors.ProductFlavorProd
import commons.flavors.ProductFlavorQa
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.internal.project.DefaultProject
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.project
import org.gradle.kotlin.dsl.support.delegates.DependencyHandlerDelegate
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AndroidModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        println("gradlePlugin info: $target | ${target.properties["android"]}")

        if (target.hasProperty("android")) {
            with(target) {
                configurePlugins()
                configureAndroidBlock()
                configureCommonDependencies()
                configureLibraryBuildVariants()
            }
        }
    }
}

internal fun Project.configurePlugins() =
    extensions.getByType<BaseExtension>().run {
        plugins.apply(Plugins.kotlinAndroid)
        plugins.apply("kotlin-kapt")

        if (isFeatureModule() || isAppModule()) {
            plugins.apply(Plugins.hiltAndroid)
            plugins.apply(Plugins.safeArgs)
        }
    }

internal fun Project.configureAndroidBlock() =
    extensions.getByType(BaseExtension::class).run {
        compileSdkVersion(ConfigData.compileSdk)

        defaultConfig {
            minSdk = ConfigData.minSdk
            targetSdk = ConfigData.targetSdk

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        val compilerArgs = listOf(
            "-Xuse-experimental=androidx.compose.ui.ExperimentalComposeUiApi",
            "-Xuse-experimental=androidx.compose.material.ExperimentalMaterialApi",
            "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xuse-experimental=kotlinx.serialization.ExperimentalSerializationApi"
        )
        tasks.withType(KotlinCompile::class).configureEach {
            kotlinOptions {
                jvmTarget = ConfigData.kotlinJvmTarget
                freeCompilerArgs = compilerArgs
            }
        }
    }

internal fun Project.configureLibraryBuildVariants() =
    extensions.getByType<BaseExtension>().run {
        if (!isAppModule()) {
            buildTypes {
                BuildTypeDebug.libraryCreate(this)
                BuildTypeRelease.libraryCreate(
                    namedDomainObjectContainer = this
                )
            }

            flavorDimensions(ConfigData.FLAVOR_ENVIRONMENT)
            productFlavors {
                ProductFlavorDev.libraryCreate(this)
                ProductFlavorQa.libraryCreate(this)
                ProductFlavorProd.libraryCreate(this)
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
    }

internal fun Project.configureCommonDependencies() {
    extensions.getByType<BaseExtension>().run {
        dependencies {
            println("module name: $name, $displayName")

            implementation(Deps.Kotlin.coroutinesCore)
            implementation(Deps.Kotlin.coroutinesAndroid)

            implementation(project(Modules.utilsLogger))

            if (isAppModule()) {
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
            }

            if (isFeatureModule() || isAppModule()) {
                implementation(project(Modules.uikit))
                implementation(project(Modules.navigation))
                implementation(project(Modules.domain))
                implementation(project(Modules.core))
                implementation(project(Modules.utilsExtensions))

                implementation(Deps.AndroidX.core)
                implementation(Deps.AndroidX.appCompat)

                implementation(Deps.Google.material)

                implementation(Deps.AndroidX.navigationFragment)
                implementation(Deps.AndroidX.navigationUi)

                implementation(Deps.Google.hilt)
                kapt(Deps.Google.hiltAndroidCompiler)
            }

            if (isDataModule()) {
                implementation(project(Modules.domain))

                implementation(Deps.Google.hilt)
                kapt(Deps.Google.hiltAndroidCompiler)
            }
        }
    }
}

private fun DependencyHandlerDelegate.implementation(dependencyNotation: Any) =
    add("implementation", dependencyNotation)

private fun DependencyHandlerDelegate.kapt(dependencyNotation: Any) =
    add("kapt", dependencyNotation)

private fun Project.isFeatureModule(): Boolean =
    displayName.contains(":feature:")

private fun Project.isAppModule(): Boolean =
    name == "app"

private fun Project.isDataModule(): Boolean =
    displayName.contains(":data:")