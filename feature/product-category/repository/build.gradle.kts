plugins {
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.dagger)
}

android {
    namespace = "com.grappim.product_category.repository"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data:network"))
    implementation(project(":data:db"))
    implementation(project(":utils:calculations"))
    implementation(project(":utils:date-time"))

    implementation(project(":common:di"))
    implementation(project(":common:db"))
    implementation(project(":common:lce"))
    implementation(project(":common:asynchronous"))

    implementation(project(":feature:product-category:domain"))
    implementation(project(":feature:product-category:db"))
    implementation(project(":feature:product-category:network"))

    implementation(libs.androidx.paging.runtime)
}
