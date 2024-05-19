plugins {
    alias(libs.plugins.cashier.java.library)
}

dependencies {
    api(libs.junit4)
    api(libs.kotlinx.coroutines.test)
    api(libs.turbine)
    api(libs.mockk)
    api(libs.kluent)
}
