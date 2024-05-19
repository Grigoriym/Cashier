plugins {
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.dagger)
}

android {
    namespace = "com.grappim.cashier.feature.bag.db"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":common:di"))
    implementation(project(":common:db"))

    implementation(project(":feature:bag:domain"))

    implementation(libs.kotlinx.coroutines.core)

    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    testImplementation(libs.androidx.room.testing)
}
