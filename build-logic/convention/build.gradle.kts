import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.grappim.cashier.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies{
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.firebase.crashlytics.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidDagger") {
            id = "cashier.android.dagger"
            implementationClass = "AndroidDaggerConventionPlugin"
        }
        register("androidLibrary") {
            id = "cashier.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "cashier.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("javaLibrary") {
            id = "cashier.java.library"
            implementationClass = "JavaLibraryConventionPlugin"
        }
    }
}
