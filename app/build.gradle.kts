plugins {
    id(Plugins.androidApplication)
    id(Plugins.grappimAndroidAppPlugin)
}

dependencies {
    implementation(Deps.AndroidX.splashScreen)
    implementation(project(Modules.utilsBiometric))
}