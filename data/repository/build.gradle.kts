import java.io.File
import java.util.Properties

plugins {
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.dagger)
    alias(libs.plugins.protobuf)
}

fun readProperties(propertiesFile: File): Properties {
    val properties = Properties()
    propertiesFile.inputStream().use { fis ->
        properties.load(fis)
    }
    return properties
}

val appPropertiesFile = File("./app.properties")
val appProperties = readProperties(appPropertiesFile)

val cashierSecretKey = appProperties.getProperty("cashier_secret_key")

android {
    namespace = "com.grappim.repository"
    buildFeatures {
        buildConfig = true
    }
    defaultConfig {
        buildConfigField(
            "String",
            "cashier_secret_key",
            cashierSecretKey
        )
    }
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
    implementation(project(":data:network"))
    implementation(project(":data:repository-api"))
    implementation(project(":data:db"))
    implementation(project(":utils:calculations"))
    implementation(project(":utils:date-time"))
    implementation(project(":utils:logger"))

    implementation(project(":domain"))
    implementation(project(":common:di"))
    implementation(project(":common:db"))
    implementation(project(":common:lce"))
    implementation(project(":common:asynchronous"))

    implementation(project(":feature:product-category:repository"))
    implementation(project(":feature:product-category:db"))
    implementation(project(":feature:product-category:domain"))

    implementation(project(":feature:sign-up:domain"))
    implementation(project(":feature:sign-up:repository"))

    implementation(project(":feature:payment-method:domain"))
    implementation(project(":feature:waybill:domain"))

    implementation(project(":feature:products:domain"))
    implementation(project(":feature:products:repository"))

    implementation(project(":feature:waybill:domain"))
    implementation(project(":feature:waybill:repository"))

    implementation(project(":feature:bag:domain"))
    implementation(project(":feature:bag:repository"))
    implementation(project(":feature:bag:db"))
    
    implementation(libs.androidx.paging.common)

    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.datastore)
    implementation(libs.androidx.datastore.prefs)
    implementation(libs.androidx.security.crypto)
    implementation(libs.google.protobuf)
}
