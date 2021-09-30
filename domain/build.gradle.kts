plugins {
    id("java-library")
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    implementation(project(Modules.logger))
    implementation(project(Modules.utilsCalculations))

    implementation(Deps.Kotlin.coroutinesCore)
    implementation(Deps.Kotlin.coroutinesAndroid)

    implementation(Deps.Google.hiltPureKotlinCore)
    kapt(Deps.Google.hiltPureKotlinCompiler)

    implementation(Deps.AndroidX.pagingCommon)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}