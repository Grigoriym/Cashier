plugins {
    id(Plugins.javaLibrary)
    kotlin(Plugins.kotlinJvm)
    kotlin(Plugins.kotlinKapt)
}

dependencies {
    implementation(Deps.Google.dagger)
    kapt(Deps.Google.daggerCompiler)

    implementation(project(Modules.commonLce))

    implementation(Deps.Kotlin.coroutinesCore)
}

java {
    sourceCompatibility = ConfigData.sourceCompatibility
    targetCompatibility = ConfigData.targetCompatibility
}