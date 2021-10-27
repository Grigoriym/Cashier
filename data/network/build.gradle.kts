import commons.flavors.ProductFlavorDev
import commons.flavors.ProductFlavorProd
import commons.flavors.ProductFlavorQa

plugins {
    id(Plugins.androidLibrary)
    id(Plugins.grappimDataPlugin)
    id(Plugins.hiltAndroid)
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

    implementation(Deps.Google.gson)

    implementation(Deps.okhttp)
    implementation(Deps.loggingInterceptor)
    implementation(Deps.retrofit)
    implementation(Deps.retrofitGsonConverter)

    debugImplementation(Deps.chucker)
    releaseImplementation(Deps.chuckerNoOp)

    coreLibraryDesugaring(Deps.desugar)
}