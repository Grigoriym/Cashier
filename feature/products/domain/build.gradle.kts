plugins {
    id(Plugins.javaLibrary)
    kotlin(Plugins.kotlinJvm)
    kotlin(Plugins.kotlinKapt)
}

dependencies {
    implementation(project(Modules.utilsLogger))
    implementation(project(Modules.utilsCalculations))
    implementation(project(Modules.commonLce))

    implementation(project(Modules.domain))
    implementation(project(Modules.featureProductCategoryDomain))

    implementation(Deps.Kotlin.coroutinesCore)
    implementation(Deps.AndroidX.pagingCommon)

    implementation(Deps.Google.dagger)
    kapt(Deps.Google.daggerCompiler)
}

java {
    sourceCompatibility = ConfigData.sourceCompatibility
    targetCompatibility = ConfigData.targetCompatibility
}