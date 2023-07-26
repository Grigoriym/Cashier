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
    namespace = "com.grappim.product_category.presentation"
}

dependencies {
    implementation(project(Modules.utilsCalculations))
    implementation(project(Modules.utilsDateTime))

    implementation(project(Modules.featureProductCategoryDomain))

    implementation(Deps.combineTupleFlow)
}