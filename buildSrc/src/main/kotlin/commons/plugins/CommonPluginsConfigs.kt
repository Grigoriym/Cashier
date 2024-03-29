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

//            detektPlugins(project(Modules.detektRules))
//            lintChecks(project(Modules.lintRules))
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

//        sourceSets {
//            getByName("main") {
//                java.srcDir("src/main/kotlin")
//            }
//        }

        compileOptions {
            sourceCompatibility = ConfigData.sourceCompatibility
            targetCompatibility = ConfigData.targetCompatibility
        }

        val compilerArgs = listOf(
            "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi",
            "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi",
            "-opt-in=androidx.compose.material.ExperimentalMaterialApi",
            "-opt-in=kotlinx.serialization.ExperimentalSerializationApi",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-opt-in=kotlinx.coroutines.FlowPreview",
            "-Xno-call-assertions",
            "-Xno-receiver-assertions",
            "-Xno-param-assertions"
        )
        tasks.withType(KotlinCompile::class).configureEach {
            kotlinOptions {
                jvmTarget = ConfigData.kotlinJvmTarget
                freeCompilerArgs = compilerArgs
                freeCompilerArgs = freeCompilerArgs + "-Xjvm-default=all"
            }
        }
    }

internal fun Project.getCommonTestDependencies() {
    extensions.getByType<BaseExtension>().run {
        dependencies {
            testImplementation(project(Modules.testShared))

            testImplementation(Deps.Testing.junit)
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

internal fun Project.ignoreCommonVariantFilters() =
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