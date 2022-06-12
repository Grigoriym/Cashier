plugins {
    id(Plugins.androidLibrary)
    id(Plugins.presentationPlugin)
    id(Plugins.kotlinParcelize)
}

android {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.AndroidX.Compose.core
    }
}

dependencies {
    implementation(project(Modules.dataWorkers))
    implementation(project(Modules.utilsBiometric))

    testImplementation(Deps.Testing.junit)
    testImplementation(Deps.Testing.assertJCore)
    testImplementation(Deps.Testing.kotlinCoroutinesTest)
    testImplementation(Deps.Testing.kotlinTest)
    testImplementation(Deps.Testing.androidCoreTesting)
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
    testImplementation("io.strikt:strikt-core:0.34.1")
}