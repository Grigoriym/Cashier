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

dependencies {
    implementation("com.android.tools.build:gradle:7.0.3")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")

    implementation(gradleApi())
    implementation(localGroovy())
}

gradlePlugin {
    plugins {
        register("com.grappim.plugin.android") {
            id = "com.grappim.plugin.android"
            implementationClass = "commons.plugins.AndroidModulePlugin"
        }
        register("com.grappim.plugin.data") {
            id = "com.grappim.plugin.data"
            implementationClass = "commons.plugins.DataModulePlugin"
        }
        register("com.grappim.plugin.android.app") {
            id = "com.grappim.plugin.android.app"
            implementationClass = "commons.plugins.AndroidAppModulePlugin"
        }
    }
}