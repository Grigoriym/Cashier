package commons.plugins

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal class AndroidLibraryModulePlugin : Plugin<Project> {
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
    }

private fun Project.configureAndroidBlock() =
    extensions.getByType<BaseExtension>().run {
        getCommonAndroidBlock()
    }

private fun Project.configureLibraryBuildVariants() =
    extensions.getByType<BaseExtension>().run {
        getCommonLibraryBuildVariants()
        ignoreCommonVariantFilters()
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

@Deprecated(
    message = "with a new way of making each feature module self-sufficient, this check becomes incorrect"
)
private fun Project.isFeatureModule(): Boolean =
    displayName.contains(":feature:") ||
      displayName.contains("presentation")