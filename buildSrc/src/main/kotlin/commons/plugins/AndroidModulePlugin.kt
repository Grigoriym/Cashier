package commons.plugins

import Plugins
import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal class AndroidModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        println("gradlePlugin info: $target | ${target.properties["android"]}")

        if (target.hasProperty("android")) {
            with(target) {
                configurePlugins()
                configureAndroidBlock()
                configureLibraryBuildVariants()
                configureCommonDependencies()
                configureCommonTestDependencies()
            }
        }
    }
}

private fun Project.configurePlugins() =
    extensions.getByType<BaseExtension>().run {
        getCommonPlugins()
        if (isFeatureModule()) {
            plugins.apply(Plugins.hiltAndroid)
            plugins.apply(Plugins.safeArgs)
        }
    }

private fun Project.configureAndroidBlock() =
    extensions.getByType<BaseExtension>().run {
        getCommonAndroidBlock()
    }

private fun Project.configureLibraryBuildVariants() =
    extensions.getByType<BaseExtension>().run {
        getCommonLibraryBuildVariants()
    }

private fun Project.configureCommonDependencies() {
    extensions.getByType<BaseExtension>().run {
        dependencies {
            println("module name: $name, $displayName")
            getCommonDependencies()
            if (isFeatureModule()) {
                getCommonAndroidDependencies()
            }
        }
    }
}

private fun Project.configureCommonTestDependencies() {
    extensions.getByType<BaseExtension>().run {
        getCommonTestDependencies()
    }
}

private fun Project.isFeatureModule(): Boolean =
    displayName.contains(":feature:")