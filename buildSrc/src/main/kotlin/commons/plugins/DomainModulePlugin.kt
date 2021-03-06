package commons.plugins

import Deps
import Plugins
import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.project

internal class DomainModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        println("gradlePlugin info: $target")

        with(target) {
            extensions.getByType<BaseExtension>().run {
                plugins.apply(Plugins.kotlinJvmFull)
                plugins.apply(Plugins.kotlinKaptFull)
                plugins.apply(Plugins.javaLibrary)

                dependencies {
                    implementation(Deps.Kotlin.coroutinesCore)
                    detektPlugins(project(Modules.detektRules))
                }
            }
            java {
                sourceCompatibility = ConfigData.sourceCompatibility
                targetCompatibility = ConfigData.targetCompatibility
            }

        }
    }
}