import org.gradle.api.JavaVersion

object ConfigData {
    const val minSdk = 23
    const val compileSdk = 34
    const val targetSdk = 34

    const val kotlinJvmTarget = "18"

    const val FLAVOR_ENVIRONMENT = "environment"

    val sourceCompatibility = JavaVersion.VERSION_18
    val targetCompatibility = JavaVersion.VERSION_18

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}