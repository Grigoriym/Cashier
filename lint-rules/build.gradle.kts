plugins {
    kotlin(Plugins.kotlinJvm)
    id(Plugins.javaLibrary)
}

java {
    sourceCompatibility = ConfigData.sourceCompatibility
    targetCompatibility = ConfigData.targetCompatibility
}

tasks.withType<Jar>() {
    manifest {
        attributes["Lint-Registry-v2"] = "com.grappim.lint_rules.LintIssueRegistry"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    compileOnly(Deps.Kotlin.core)

    compileOnly(Deps.Lint.api)
    compileOnly(Deps.Lint.checks)
    testImplementation(Deps.Lint.lint)
    testImplementation(Deps.Lint.lintTests)
    testImplementation(Deps.Lint.testUtils)

    testImplementation(Deps.Kotest.runnerJunit)
    testImplementation(Deps.Kotest.assertionsCore)
}