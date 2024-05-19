plugins {
    alias(libs.plugins.cashier.android.library)
    alias(libs.plugins.cashier.android.dagger)
    alias(libs.plugins.protobuf)
}

android {
    namespace = "com.grappim.feature.products.repository"
}

dependencies {
    implementation(project(":data:db"))
    implementation(project(":utils:date-time"))

    implementation(project(":common:di"))
    implementation(project(":common:db"))
    implementation(project(":common:lce"))
    implementation(project(":common:asynchronous"))

    implementation(project(":feature:products:domain"))
    implementation(project(":feature:products:network"))
    implementation(project(":feature:product-category:domain"))

    implementation(project(":feature:bag:network"))
    implementation(project(":feature:bag:db"))

    implementation(project(":domain"))

    implementation(libs.androidx.paging.runtime)
    implementation(libs.kotlinx.coroutines.core)

    implementation(libs.androidx.room.ktx)
}
