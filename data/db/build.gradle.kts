plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimDataPlugin)
    id(Plugins.kotlinParcelize)
}

android {
    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        kapt {
            arguments {
                arg("room.incremental", "true")
                arg("room.expandProjection", "true")
                arg("room.schemaLocation", "$projectDir/schemas")
            }
        }
    }
    namespace = "com.grappim.db"
}

dependencies {
    implementation(project(Modules.utilsCalculations))
    implementation(project(Modules.commonDi))
    implementation(project(Modules.commonDb))

    implementation(project(Modules.domain))

    implementation(project(Modules.featureProductCategoryDb))
    implementation(project(Modules.featureBagDb))

    implementation(Deps.Kotlin.serialization)

    api(Deps.AndroidX.roomCore)
    kapt(Deps.AndroidX.roomCompiler)
}