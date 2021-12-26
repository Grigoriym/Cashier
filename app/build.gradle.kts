plugins {
    id(Plugins.androidApplication)
    id(Plugins.grappimAndroidAppPlugin)
    id(Plugins.detekt)
    id(Plugins.scabbard)
}

scabbard {
    enabled = true
    outputFormat = "png"
}