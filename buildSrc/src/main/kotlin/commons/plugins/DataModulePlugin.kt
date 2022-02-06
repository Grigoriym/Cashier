package commons.plugins

import Deps
import Modules
import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.project

internal class DataModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        println("gradlePlugin info: $target | ${target.properties["android"]}")

        if (target.hasProperty("android")) {
            with(target) {
                configurePlugins()
                configureAndroidBlock()
                configureLibraryBuildVariants()
                configureDependencies()
                configureCommonTestDependencies()
            }
        }
    }
}

private fun Project.configurePlugins() =
    extensions.getByType<BaseExtension>().run {
        getCommonPlugins()
    }

private fun Project.configureAndroidBlock() =
    extensions.getByType<BaseExtension>().run {
        getCommonAndroidBlock()
    }

private fun Project.configureLibraryBuildVariants() =
    extensions.getByType<BaseExtension>().run {
        getCommonLibraryBuildVariants()
        getCommonVariantFilters()
    }

private fun Project.configureDependencies() {
    extensions.getByType<BaseExtension>().run {
        dependencies {
            println("module name: $name, $displayName")

            getCommonDependencies()

            implementation(project(Modules.domain))

            implementation(Deps.Google.dagger)
            kapt(Deps.Google.daggerCompiler)
        }
    }
}

private fun Project.configureCommonTestDependencies() {
    extensions.getByType<BaseExtension>().run {
        getCommonTestDependencies()
    }
}
