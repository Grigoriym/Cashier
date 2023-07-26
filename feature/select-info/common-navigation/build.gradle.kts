plugins {
  id(Plugins.androidLibrary)
  id(Plugins.grappimAndroidLibraryPlugin)
}

dependencies {
  implementation(project(Modules.navigation))
}
android {
    namespace = "com.grappim.select_info.common_navigation"
}
