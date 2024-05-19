import com.grappim.cashier.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class JavaLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("java-library")
                apply("org.jetbrains.kotlin.jvm")
                apply("com.google.devtools.ksp")
            }
            project.configure<JavaPluginExtension> {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }
            project.configure<KotlinJvmProjectExtension> {
                tasks.withType<KotlinCompile>().configureEach {
                    kotlinOptions {
                        jvmTarget = JavaVersion.VERSION_17.toString()
                    }
                }
            }

            dependencies {
                "implementation"(libs.findLibrary("kotlinx.coroutines.core").get())

                "implementation"(libs.findLibrary("dagger").get())
                "ksp"(libs.findLibrary("daggerCompiler").get())
                "kspTest"(libs.findLibrary("daggerCompiler").get())

                add("testImplementation", kotlin("test"))
                add("testImplementation", project(":test-shared"))
            }
        }
    }
}
