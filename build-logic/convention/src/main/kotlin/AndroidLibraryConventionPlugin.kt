import com.android.build.gradle.LibraryExtension
import com.grappim.cashier.configureKotlinAndroid
import com.grappim.cashier.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.project

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = libs.findVersion("targetSdk").get().toString().toInt()
            }

            dependencies {
                add("testImplementation", kotlin("test"))
                add("testImplementation", project(":test-shared"))
                add("androidTestImplementation", kotlin("test"))
                add("androidTestImplementation", project(":test-shared-android"))
                add("androidTestImplementation", project(":test-shared"))
            }
        }
    }
}
