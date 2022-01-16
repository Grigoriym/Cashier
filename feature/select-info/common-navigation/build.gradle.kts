plugins {
  id(Plugins.androidLibrary)
  id(Plugins.grappimAndroidLibraryPlugin)
}

dependencies {
  implementation(project(Modules.navigation))
}