plugins {
    id(Plugins.javaLibrary)
    kotlin(Plugins.kotlinJvm)
    kotlin(Plugins.kotlinKapt)
}

dependencies {
    implementation(project(Modules.utilsLogger))
    implementation(project(Modules.utilsCalculations))
    implementation(project(Modules.commonDi))
    implementation(project(Modules.commonLce))
    implementation(project(Modules.commonAsynchronous))

    implementation(project(Modules.featureProductCategoryDomain))

    implementation(Deps.Kotlin.coroutinesCore)

    implementation(Deps.Google.dagger)
    kapt(Deps.Google.daggerCompiler)

    implementation(Deps.AndroidX.pagingCommon)
}

java {
    sourceCompatibility = ConfigData.sourceCompatibility
    targetCompatibility = ConfigData.targetCompatibility
}