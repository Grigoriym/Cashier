package commons.plugins

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.project

internal class PresentationModulePlugin : Plugin<Project> {
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
            getCommonAndroidDependencies()

            implementation(Deps.cicerone)

            implementation(project(Modules.commonDi))
            implementation(project(Modules.commonAsynchronous))
            implementation(project(Modules.commonLce))

            implementation(platform(Deps.Compose.bom))

            implementation(Deps.Compose.ui)
            implementation(Deps.Compose.material)
            implementation(Deps.Compose.toolingPreview)
            implementation(Deps.Compose.uiTooling)
            implementation(Deps.Compose.runtime)
            implementation(Deps.Compose.runtimeLivedata)
            implementation(Deps.Compose.foundation)
            implementation(Deps.Compose.foundationLayout)
            implementation(Deps.Compose.icons)
            implementation(Deps.Compose.constraint)
            implementation(Deps.Compose.lifecycleViewModel)
        }
    }
}

private fun Project.configureCommonTestDependencies() {
    extensions.getByType<BaseExtension>().run {
        getCommonTestDependencies()
    }
}