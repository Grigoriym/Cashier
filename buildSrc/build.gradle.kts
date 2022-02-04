plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

val gradlePlugin = "7.0.4"
val kotlinCore = "1.6.10"

dependencies {
    implementation("com.android.tools.build:gradle:${gradlePlugin}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlinCore}")

    implementation(gradleApi())
    implementation(localGroovy())
}

gradlePlugin {
    plugins {
        register("com.grappim.plugin.android.library") {
            id = "com.grappim.plugin.android.library"
            implementationClass = "commons.plugins.AndroidLibraryModulePlugin"
        }
        register("com.grappim.plugin.data") {
            id = "com.grappim.plugin.data"
            implementationClass = "commons.plugins.DataModulePlugin"
        }
        register("com.grappim.plugin.android.app") {
            id = "com.grappim.plugin.android.app"
            implementationClass = "commons.plugins.AndroidAppModulePlugin"
        }
        register("com.grappim.plugin.domain") {
            id = "com.grappim.plugin.domain"
            implementationClass = "commons.plugins.DomainModulePlugin"
        }
        register("com.grappim.plugin.presentation") {
            id = "com.grappim.plugin.presentation"
            implementationClass = "commons.plugins.PresentationModulePlugin"
        }
    }
}