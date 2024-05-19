plugins {
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.dagger)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.grappim.cashier.network"
    buildFeatures {
        buildConfig = true
    }
    defaultConfig {
        buildTypes{
            getByName("debug") {
                buildConfigField(
                    "String",
                    "CASHIER_API",
                    "\"http://127.0.0.1:5000/api/v1/\""
                )
             }
            getByName("release") {
                buildConfigField(
                    "String",
                    "CASHIER_API",
                    "\"http://127.0.0.1:5000/api/v1/\""
                )
            }
        }
    }
}

dependencies {
    implementation(project(":data:db"))
    implementation(project(":utils:date-time"))
    implementation(project(":utils:logger"))

    implementation(project(":common:di"))
    implementation(project(":common:asynchronous"))
    implementation(project(":common:lce"))
    implementation(project(":common:annotations"))
    implementation(project(":common:network:serializers"))

    implementation(project(":navigation"))
    implementation(project(":domain"))

    implementation(project(":feature:product-category:network"))
    implementation(project(":feature:bag:domain"))
    implementation(project(":feature:products:domain"))

    implementation(libs.kotlinx.serialization)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.okhttp)
    implementation(libs.loggingInterceptor)
    implementation(libs.retrofit)
    implementation(libs.retrofitKotlinSerializationConverter)

    debugImplementation(libs.chucker)
    releaseImplementation(libs.chuckerNoOp)
}
