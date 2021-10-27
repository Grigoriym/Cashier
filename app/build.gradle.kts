plugins {
    id(Plugins.androidApplication)
    id(Plugins.grappimAndroidAppPlugin)
    id(Plugins.detekt)
}

android {
    hilt {
        enableAggregatingTask = true
    }
}