plugins {
    id(Plugins.javaLibrary)
    kotlin(Plugins.kotlinJvm)
    kotlin(Plugins.kotlinKapt)
}

dependencies {
    implementation(project(Modules.utilsLogger))
    implementation(project(Modules.utilsCalculations))
    implementation(project(Modules.di))

    implementation(Deps.Kotlin.coroutinesCore)

    implementation(Deps.Google.dagger)
    kapt(Deps.Google.daggerCompiler)

    implementation(Deps.AndroidX.pagingCommon)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}