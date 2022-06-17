import org.gradle.api.JavaVersion

object ConfigData {
    const val minSdk = 23
    const val compileSdk = 32
    const val targetSdk = 32

    const val kotlinJvmTarget = "1.8"

    const val FLAVOR_ENVIRONMENT = "environment"

    val sourceCompatibility = JavaVersion.VERSION_1_8
    val targetCompatibility = JavaVersion.VERSION_1_8

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}