import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import commons.buildTypes.BuildTypeDebug
import commons.buildTypes.BuildTypeRelease
import io.gitlab.arturbosch.detekt.Detekt

buildscript {
    dependencies {
        classpath(BuildPlugins.androidGradle)

        classpath(BuildPlugins.kotlin)
        classpath(BuildPlugins.kotlinSerialization)

        classpath(BuildPlugins.googleServices)
        classpath(BuildPlugins.crashlytics)
    }
}

plugins {
    id(Plugins.scabbard) version Versions.scabbard
    id(Plugins.gradleVersions) version Versions.gradleVersions
    id(Plugins.detekt) version Versions.detekt
//    id(Plugins.depGraphGenerator) version Versions.graphGenerator
}

//dependencyGraphGenerator {
//
//}

scabbard {
    enabled = false
    outputFormat = "png"
}

subprojects {
    apply {
        plugin(Plugins.detekt)
        plugin(Plugins.scabbard)
//        plugin(Plugins.depGraphGenerator)
    }

    scabbard {
        enabled = false
        outputFormat = "png"
    }

    detekt {
        buildUponDefaultConfig = true
        toolVersion = Versions.detekt
        config.setFrom("${rootDir}/config/detekt/detekt.yml")
        source.setFrom("src/main/java", "src/main/kotlin")
        parallel = true

        ignoreFailures = true
        ignoredBuildTypes = listOf(BuildTypeRelease.name, BuildTypeDebug.name)
        disableDefaultRuleSets = false
    }

}

dependencies {
    detektPlugins(Deps.Detekt.formatting)
    detektPlugins(project(Modules.detektRules))
}

tasks.withType<Detekt>().configureEach {
    jvmTarget = ConfigData.kotlinJvmTarget

    dependsOn("${Modules.detektRules}:assemble")

    reports {
        xml.required.set(true)
        xml.outputLocation.set(file("build/reports/detekt.xml"))

        html.required.set(true)
        html.outputLocation.set(file("build/reports/detekt.html"))
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