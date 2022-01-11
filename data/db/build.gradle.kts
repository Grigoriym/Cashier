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
                arg("room.schemaLocation", "$projectDir/schemas")
            }
        }
    }
}

dependencies {
    implementation(project(Modules.utilsCalculations))
    implementation(project(Modules.commonDi))
    implementation(project(Modules.featureProductCategoryDb))
    implementation(project(Modules.commonDb))

    implementation(Deps.Kotlin.serialization)

    api(Deps.AndroidX.roomCore)
    kapt(Deps.AndroidX.roomCompiler)
}