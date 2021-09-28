plugins {
    id("java-library")
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    implementation(Deps.Google.hiltPureKotlinCore)
    kapt(Deps.Google.hiltPureKotlinCompiler)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}