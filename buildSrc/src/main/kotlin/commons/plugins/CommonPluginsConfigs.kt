package commons.plugins

import ConfigData
import Deps
import Modules
import Plugins
import com.android.build.gradle.BaseExtension
import commons.buildTypes.BuildTypeDebug
import commons.buildTypes.BuildTypeRelease
import commons.flavors.ProductFlavorDev
import commons.flavors.ProductFlavorProd
import commons.flavors.ProductFlavorQa
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.getCommonPlugins() =
    extensions.getByType<BaseExtension>().run {
        plugins.apply(Plugins.kotlinAndroid)
        plugins.apply(Plugins.kotlinKaptFull)
    }

internal fun Project.getCommonDependencies() =
    extensions.getByType<BaseExtension>().run {
        dependencies {
            implementation(Deps.Kotlin.coroutinesCore)
            implementation(Deps.Kotlin.coroutinesAndroid)

            implementation(project(Modules.utilsLogger))
        }
    }

internal fun Project.getCommonAndroidDependencies() =
    extensions.getByType<BaseExtension>().run {
        dependencies {
            implementation(project(Modules.uikit))
            implementation(project(Modules.navigation))
            implementation(project(Modules.domain))
            implementation(project(Modules.core))
            implementation(project(Modules.utilsExtensions))

            implementation(Deps.AndroidX.core)
            implementation(Deps.AndroidX.appCompat)
            implementation(Deps.AndroidX.fragment)
            implementation(Deps.AndroidX.activity)

            implementation(Deps.Google.material)

            implementation(Deps.AndroidX.navigationFragment)
            implementation(Deps.AndroidX.navigationUi)

            implementation(Deps.Google.dagger)
            kapt(Deps.Google.daggerCompiler)
        }
    }

internal fun Project.getCommonAndroidBlock() =
    extensions.getByType<BaseExtension>().run {
        compileSdkVersion(ConfigData.compileSdk)

        defaultConfig {
            minSdk = ConfigData.minSdk
            targetSdk = ConfigData.targetSdk

            testInstrumentationRunner = ConfigData.testInstrumentationRunner
        }

        compileOptions {
            sourceCompatibility = ConfigData.sourceCompatibility
            targetCompatibility = ConfigData.targetCompatibility
        }

        val compilerArgs = listOf(
            "-Xuse-experimental=androidx.compose.ui.ExperimentalComposeUiApi",
            "-Xuse-experimental=androidx.compose.foundation.ExperimentalFoundationApi",
            "-Xuse-experimental=androidx.compose.material.ExperimentalMaterialApi",
            "-Xuse-experimental=kotlinx.serialization.ExperimentalSerializationApi",
            "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xuse-experimental=kotlinx.coroutines.FlowPreview"
        )
        tasks.withType(KotlinCompile::class).configureEach {
            kotlinOptions {
                jvmTarget = ConfigData.kotlinJvmTarget
                freeCompilerArgs = compilerArgs
            }
        }
    }

internal fun Project.getCommonTestDependencies() {
    extensions.getByType<BaseExtension>().run {
        dependencies {
            testImplementation(Deps.Testing.junit)
            testImplementation(Deps.Testing.assertJCore)
            testImplementation(Deps.Testing.kotlinTest)
            testImplementation(Deps.Testing.kotlinCoroutinesTest)
            testImplementation(Deps.Testing.androidCoreTesting)

            androidTestImplementation(Deps.Testing.androidJunit)
            androidTestImplementation(Deps.Testing.androidEspressoCore)
        }
    }
}

internal fun Project.getCommonLibraryBuildVariants() =
    extensions.getByType<BaseExtension>().run {
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
    }

internal fun Project.getCommonVariantFilters() =
    extensions.getByType<BaseExtension>().run {
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