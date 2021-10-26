plugins {
    id(Plugins.javaLibrary)
    kotlin(Plugins.kotlinJvm)
    kotlin(Plugins.kotlinKapt)
}

dependencies {
    implementation(project(Modules.utilsLogger))
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