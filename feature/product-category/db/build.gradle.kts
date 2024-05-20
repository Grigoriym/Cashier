plugins {
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.dagger)
}

android {
    namespace = "com.grappim.cashier.feature.productcategory.db"
}

dependencies {
    implementation(project(":utils:calculations"))
    implementation(project(":common:di"))
    implementation(project(":common:db"))

    implementation(project(":feature:product-category:domain"))

    implementation(libs.kotlinx.serialization)

    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    testImplementation(libs.androidx.room.testing)
}
