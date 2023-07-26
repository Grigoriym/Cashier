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
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
    namespace = "com.grappim.products.presentation"
}

dependencies {
    implementation(project(Modules.utilsCalculations))
    implementation(project(Modules.utilsDateTime))

    implementation(project(Modules.featureProductCategoryDomain))
    implementation(project(Modules.featureProductsDomain))
    implementation(project(Modules.featureProductsNetwork))
    implementation(project(Modules.featureProductsRepository))

    implementation(project(Modules.domain))

    implementation(Deps.combineTupleFlow)

    coreLibraryDesugaring(Deps.desugar)

    testImplementation(Deps.Mockk.core)
    testImplementation(Deps.kluent)
    testImplementation(Deps.turbine)
    testImplementation(Deps.Testing.androidCoreTesting)
    testImplementation(project(Modules.testSharedAndroid))
}