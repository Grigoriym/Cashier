plugins {
  id(Plugins.androidLibrary)
  id(Plugins.grappimAndroidLibraryPlugin)
}

android {

}

dependencies {
  implementation(project(Modules.commonDi))
  implementation(project(Modules.commonLce))
  implementation(project(Modules.commonAsynchronous))

  implementation(Deps.AndroidX.viewPager2)

  implementation(project(Modules.selectInfoSelectCashbox))
  implementation(project(Modules.selectInfoSelectStock))
  implementation(project(Modules.featureSelectInfoNavigation))
}