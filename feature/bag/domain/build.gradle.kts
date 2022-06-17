plugins {
    id(Plugins.javaLibrary)
    kotlin(Plugins.kotlinJvm)
    kotlin(Plugins.kotlinKapt)
}

dependencies {
    implementation(project(Modules.commonLce))
    implementation(project(Modules.utilsCalculations))
    implementation(project(Modules.domain))

    implementation(Deps.Kotlin.coroutinesCore)
    implementation(Deps.Google.dagger)
    kapt(Deps.Google.daggerCompiler)
}

java {
    sourceCompatibility = ConfigData.sourceCompatibility
    targetCompatibility = ConfigData.targetCompatibility
}