plugins {
    id(Plugins.androidLibrary)
    id(Plugins.presentationPlugin)
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
    implementation(project(Modules.featureSignUpDomain))
    implementation(project(Modules.featureSignUpRepository))

    implementation(project(Modules.featureAuthNetwork))

    implementation(project(Modules.dataNetwork))
}