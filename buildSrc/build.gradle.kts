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
        register("com.grappim.plugin.domain"){
            id = "com.grappim.plugin.domain"
            implementationClass = "commons.plugins.DomainModulePlugin"
        }
    }
}