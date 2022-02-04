package commons.plugins

import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.accessors.runtime.addDependencyTo
import org.gradle.kotlin.dsl.support.delegates.DependencyHandlerDelegate
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

internal fun DependencyHandlerDelegate.implementation(dependencyNotation: Any) =
    add("implementation", dependencyNotation)

internal fun DependencyHandlerDelegate.testImplementation(dependencyNotation: Any) =
    add("testImplementation", dependencyNotation)

internal fun DependencyHandlerDelegate.androidTestImplementation(dependencyNotation: Any) =
    add("androidTestImplementation", dependencyNotation)

internal fun DependencyHandlerDelegate.kapt(dependencyNotation: Any) =
    add("kapt", dependencyNotation)

internal fun DependencyHandler.coreLibraryDesugaring(dependencyNotation: Any): Dependency? =
    add("coreLibraryDesugaring", dependencyNotation)

internal fun DependencyHandler.debugImplementation(dependencyNotation: Any): Dependency? =
    add("debugImplementation", dependencyNotation)

internal fun DependencyHandler.implementation(
    dependencyNotation: String,
    dependencyConfiguration: Action<ExternalModuleDependency>
): ExternalModuleDependency = addDependencyTo(
    dependencies = this,
    configuration = "implementation",
    dependencyNotation = dependencyNotation,
    configurationAction = dependencyConfiguration
) as ExternalModuleDependency

internal fun Project.kapt(configure: Action<KaptExtension>): Unit =
    (this as ExtensionAware).extensions.configure("kapt", configure)

fun Project.java(configure: Action<org.gradle.api.plugins.JavaPluginExtension>): Unit =
    (this as ExtensionAware).extensions.configure("java", configure)

fun DependencyHandler.`detektPlugins`(dependencyNotation: Any): Dependency? =
    add("detektPlugins", dependencyNotation)

fun DependencyHandler.`lintChecks`(dependencyNotation: Any): Dependency? =
    add("lintChecks", dependencyNotation)