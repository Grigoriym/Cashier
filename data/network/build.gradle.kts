import commons.flavors.ProductFlavorDev
import commons.flavors.ProductFlavorProd
import commons.flavors.ProductFlavorQa

plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimDataPlugin)
    id(Plugins.kotlinSerialization)
}

android {
    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        productFlavors {
            getByName(ProductFlavorDev.name) {
                //if you want to connect to the smartphone, use the laptop's ip address here
                buildConfigField(
                    "String",
                    "CASHIER_API",
//                    "\"http://127.0.0.1:5000/api/v1/\""
//                    "\"http://127.0.0.1:8081/api/v1/\""
//                    "\"http://192.168.0.139:8081/api/v1/\""
                    "\"http://10.0.2.2:8081/api/v1/\""
                )
            }
            getByName(ProductFlavorQa.name) {
                buildConfigField(
                    "String",
                    "CASHIER_API",
                    "\"https://quiet-shore-01215.herokuapp.com/\""
                )
            }
            getByName(ProductFlavorProd.name) {
                buildConfigField(
                    "String",
                    "CASHIER_API",
                    "\"https://quiet-shore-01215.herokuapp.com/\""
                )
            }
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
    namespace = "com.grappim.network"
}

dependencies {
    implementation(project(Modules.dataDb))
    implementation(project(Modules.utilsDateTime))

    implementation(project(Modules.commonDi))
    implementation(project(Modules.commonAsynchronous))
    implementation(project(Modules.commonLce))
    implementation(project(Modules.commonNetworkSerializers))
    implementation(project(Modules.commonAnnotations))
    implementation(project(Modules.navigation))

    implementation(project(Modules.featureProductCategoryNetwork))
    implementation(project(Modules.featureBagDomain))
    implementation(project(Modules.featureProductsDomain))

    implementation(Deps.Kotlin.serialization)

    api(Deps.okhttp)
    api(Deps.loggingInterceptor)
    api(Deps.retrofit)
    api(Deps.retrofitKotlinSerializationConverter)

    debugApi(Deps.chucker)
    releaseApi(Deps.chuckerNoOp)

    coreLibraryDesugaring(Deps.desugar)
}