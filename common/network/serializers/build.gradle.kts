plugins {
    id(Plugins.javaLibrary)
    kotlin(Plugins.kotlinJvm)
    id(Plugins.kotlinSerialization)
}

dependencies {
    implementation(Deps.Kotlin.serialization)
}

java {
    sourceCompatibility = ConfigData.sourceCompatibility
    targetCompatibility = ConfigData.targetCompatibility
}