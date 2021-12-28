import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(BuildPlugins.androidGradle)

        classpath(BuildPlugins.kotlin)
        classpath(BuildPlugins.kotlinSerialization)

        classpath(BuildPlugins.safeArgs)

        classpath(BuildPlugins.detekt)

        classpath(BuildPlugins.googleServices)
    }
}

plugins {
    id(Plugins.scabbard) version Versions.scabbard
    id(Plugins.gradleVersions) version Versions.gradleVersions
}

scabbard {
    enabled = true
    outputFormat = "png"
}

allprojects {
    repositories {
        google()
        mavenCentral()

        maven { setUrl("https://jitpack.io") }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version) && !isNonStable(currentVersion)
    }

    checkForGradleUpdate = true
    outputFormatter = "json"
    outputDir = "build/dependencyUpdates"
    reportfileName = "report"
}