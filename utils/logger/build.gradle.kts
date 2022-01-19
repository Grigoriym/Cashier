plugins {
    id(Plugins.javaLibrary)
    kotlin(Plugins.kotlinJvm)
}

java {
    sourceCompatibility = ConfigData.sourceCompatibility
    targetCompatibility = ConfigData.targetCompatibility
}