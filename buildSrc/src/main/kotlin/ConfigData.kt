import org.gradle.api.JavaVersion

object ConfigData {
    const val minSdk = 21
    const val compileSdk = 31
    const val targetSdk = 31

    const val kotlinJvmTarget = "1.8"

    const val FLAVOR_ENVIRONMENT = "environment"

    val sourceCompatibility = JavaVersion.VERSION_1_8
    val targetCompatibility = JavaVersion.VERSION_1_8

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}