plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimDataPlugin)
    id(Plugins.kotlinParcelize)
}

android {
    sourceSets {
        getByName("test") {
            kotlin.setSrcDirs(
                project(
                    Modules.featureProductCategoryDomain
                ).files("src/test/kotlin")
            )
        }
    }
    namespace = "com.grappim.product_category.db"
}

dependencies {
    implementation(project(Modules.utilsCalculations))
    implementation(project(Modules.commonDi))
    implementation(project(Modules.commonDb))

    implementation(project(Modules.featureProductCategoryDomain))

    implementation(Deps.Kotlin.serialization)

    api(Deps.AndroidX.roomCore)
    kapt(Deps.AndroidX.roomCompiler)
}