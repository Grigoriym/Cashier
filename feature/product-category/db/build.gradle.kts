plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimDataPlugin)
    id(Plugins.kotlinParcelize)
}

android {
    defaultConfig {
        kapt {
            arguments {
                arg("room.incremental", "true")
                arg("room.expandProjection", "true")
            }
        }
    }
    sourceSets {
        getByName("test") {
            kotlin.setSrcDirs(
                project(
                    Modules.featureProductCategoryDomain
                ).files("src/test/kotlin")
            )
        }
    }
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