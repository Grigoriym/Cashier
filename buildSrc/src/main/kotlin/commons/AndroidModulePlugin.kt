package commons

import ConfigData
import Deps
import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.support.delegates.DependencyHandlerDelegate
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AndroidModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        println("gradlePlugin info: $target | ${target.properties["android"]}")
        if (target.hasProperty("android")) {
            with(target) {
                plugins.apply("kotlin-android")
                plugins.apply("kotlin-kapt")

                configureAndroidBlock()
                configureCommonDependencies()
            }
        }
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

internal fun Project.configureCommonDependencies() {
    extensions.getByType<BaseExtension>().run {
        dependencies {
            implementation(Deps.Kotlin.coroutinesCore)
            implementation(Deps.Kotlin.coroutinesAndroid)

            if (name.contains("feature")) {
                implementation(Deps.AndroidX.core)
                implementation(Deps.AndroidX.appCompat)
            }
        }
    }
}

private fun DependencyHandlerDelegate.implementation(dependencyNotation: String) =
    add("implementation", dependencyNotation)