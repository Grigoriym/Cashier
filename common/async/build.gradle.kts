plugins {
    alias(libs.plugins.cashier.java.library)
}

dependencies {
    implementation(project(":common:di"))
}
