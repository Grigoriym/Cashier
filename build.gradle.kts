import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import io.gitlab.arturbosch.detekt.Detekt

buildscript {
    dependencies {
        classpath(BuildPlugins.androidGradle)

        classpath(BuildPlugins.kotlin)
        classpath(BuildPlugins.kotlinSerialization)

        classpath(BuildPlugins.safeArgs)

        classpath(BuildPlugins.googleServices)
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
    enabled = true
    outputFormat = "png"
}

subprojects {
    apply {
        plugin(Plugins.detekt)
        plugin(Plugins.scabbard)
//        plugin(Plugins.depGraphGenerator)
    }

    scabbard {
        enabled = true
        outputFormat = "png"
    }

    detekt {
        buildUponDefaultConfig = true
        toolVersion = Versions.detekt
        config = rootProject.files("${rootDir}/config/detekt/detekt.yml")
        source = files("src/main/java", "src/main/kotlin")
        parallel = true

        ignoreFailures = false
        autoCorrect = true
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