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
                    "\"http://192.168.0.102:8081/api/v1/\""
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
    
    implementation(project(Modules.featureProductCategoryNetwork))

    implementation(Deps.Rx2.retrofitAdapter)

    api(Deps.Google.gson)
    implementation(Deps.Kotlin.serialization)

    api(Deps.okhttp)
    api(Deps.loggingInterceptor)
    api(Deps.retrofit)
    api(Deps.retrofitGsonConverter)
    api(Deps.retrofitKotlinSerializationConverter)

    debugApi(Deps.chucker)
    releaseApi(Deps.chuckerNoOp)

    coreLibraryDesugaring(Deps.desugar)
}