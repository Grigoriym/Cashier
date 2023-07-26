plugins {
    id(Plugins.androidLibrary)
    id(Plugins.presentationPlugin)
}

android {
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.AndroidX.Compose.core
    }
    namespace = "com.grappim.sign_up_presentation"
}

dependencies {
    implementation(project(Modules.featureSignUpDomain))
    implementation(project(Modules.featureSignUpRepository))

    implementation(project(Modules.featureAuthNetwork))

    implementation(project(Modules.dataNetwork))
}