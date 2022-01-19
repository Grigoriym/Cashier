plugins {
    id(Plugins.javaLibrary)
    kotlin(Plugins.kotlinJvm)
    kotlin(Plugins.kotlinKapt)
}

dependencies {
    implementation(project(Modules.commonDi))

    implementation(Deps.Google.dagger)
    kapt(Deps.Google.daggerCompiler)

    testImplementation(Deps.Testing.junit)
}

java {
    sourceCompatibility = ConfigData.sourceCompatibility
    targetCompatibility = ConfigData.targetCompatibility
}