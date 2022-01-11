plugins {
    id(Plugins.androidApplication)
    id(Plugins.grappimAndroidAppPlugin)
    id(Plugins.detekt)
    id(Plugins.scabbard)
}

android{
    packagingOptions {
        resources.excludes.add("META-INF/licenses/**")
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
    }
}

scabbard {
    enabled = true
    outputFormat = "png"
}