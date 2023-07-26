plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimDataPlugin)
    id(Plugins.protobuf) version Versions.protobufPlugin
}

android {
    buildFeatures {
        buildConfig = true
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
    defaultConfig {
        buildConfigField(
            "String",
            "cashier_secret_key",
            "${extra["cashier_secret_key"]}"
        )
    }
    namespace = "com.grappim.repository"
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.23.4"
    }
    generateProtoTasks {
        all().forEach { task ->
            task.plugins {
                create("java") {
                    option("lite")
                }
            }
        }
    }
}


dependencies {
    implementation(project(Modules.dataNetwork))
    implementation(project(Modules.dataDb))
    implementation(project(Modules.utilsCalculations))
    implementation(project(Modules.utilsDateTime))

    implementation(project(Modules.commonDi))
    implementation(project(Modules.commonDb))
    implementation(project(Modules.commonLce))
    implementation(project(Modules.commonAsynchronous))

    implementation(project(Modules.featureProductCategoryRepository))
    implementation(project(Modules.featureProductCategoryDb))
    implementation(project(Modules.featureProductCategoryDomain))

    implementation(project(Modules.featureSignUpDomain))
    implementation(project(Modules.featureSignUpRepository))

    implementation(project(Modules.featurePaymentMethodDomain))
    implementation(project(Modules.featureWaybillDomain))

    implementation(project(Modules.featureProductsDomain))
    implementation(project(Modules.featureProductsRepository))

    implementation(project(Modules.featureWaybillDomain))
    implementation(project(Modules.featureWaybillRepository))

    implementation(project(Modules.featureBagDomain))
    implementation(project(Modules.featureBagRepository))
    implementation(project(Modules.featureBagDb))

    implementation(Deps.AndroidX.paging)
    implementation(Deps.AndroidX.dataStore)
    implementation(Deps.AndroidX.dataStorePreferences)
    implementation(Deps.Google.protobuf)
    implementation(Deps.AndroidX.securityCrypto)

    coreLibraryDesugaring(Deps.desugar)
}