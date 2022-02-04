plugins {
    kotlin(Plugins.kotlinJvm)
    id(Plugins.javaLibrary)
}

java {
    sourceCompatibility = ConfigData.sourceCompatibility
    targetCompatibility = ConfigData.targetCompatibility
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    compileOnly(Deps.Detekt.api)
    testImplementation(Deps.Detekt.api)
    testImplementation(Deps.Detekt.test) {
        exclude(group = "org.assertj", module = "spek-dsl-jvm")
        exclude(group = "org.spekframework.spek2", module = "assertj-core")
    }

    testImplementation(Deps.Kotest.runnerJunit)
    testImplementation(Deps.Kotest.assertionsCore)
}