import commons.flavors.ProductFlavorDev
import commons.flavors.ProductFlavorProd
import commons.flavors.ProductFlavorQa

plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimDataPlugin)
    id(Plugins.kotlinSerialization)
}

android {
    defaultConfig {
        productFlavors {
            getByName(ProductFlavorDev.name) {
                buildConfigField(
                    "String",
                    "CASHIER_API",
//                    "\"http://127.0.0.1:8081/api/v1/\""
                    "\"http://192.168.0.105:8081/api/v1/\""
//                    "\"http://10.0.2.2:8081/api/v1/\""
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

    implementation(Deps.Kotlin.serialization)

    api(Deps.okhttp)
    api(Deps.loggingInterceptor)
    api(Deps.retrofit)
    api(Deps.retrofitKotlinSerializationConverter)

    debugApi(Deps.chucker)
    releaseApi(Deps.chuckerNoOp)

    coreLibraryDesugaring(Deps.desugar)
}