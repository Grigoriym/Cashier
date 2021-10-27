plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimDataPlugin)
    id(Plugins.kotlinParcelize)
}

android {
    defaultConfig {
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true"
                )
            }
        }
    }
}

dependencies {
    implementation(project(Modules.utilsCalculations))

    implementation(Deps.Kotlin.serialization)

    api(Deps.AndroidX.roomCore)
    kapt(Deps.AndroidX.roomCompiler)
}