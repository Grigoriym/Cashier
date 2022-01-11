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
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}