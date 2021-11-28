plugins {
    id(Plugins.javaLibrary)
    kotlin(Plugins.kotlinJvm)
    kotlin(Plugins.kotlinKapt)
}

dependencies {
    implementation(Deps.Google.hiltPureKotlinCore)
    kapt(Deps.Google.hiltPureKotlinCompiler)

    testImplementation(Deps.Testing.junit)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}