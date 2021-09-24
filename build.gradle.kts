buildscript {

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath(BuildPlugins.androidGradle)

        classpath(BuildPlugins.kotlin)
        classpath(BuildPlugins.kotlinSerialization)

        classpath(BuildPlugins.hilt)

        classpath(BuildPlugins.safeArgs)

        classpath(BuildPlugins.detekt)
    }
}

allprojects {
    repositories {
        google()
        maven { setUrl("https://jitpack.io") }
        mavenCentral()
    }
}
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}