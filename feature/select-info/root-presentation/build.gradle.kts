plugins {
  id(Plugins.androidLibrary)
  id(Plugins.presentationPlugin)
}

android {

    namespace = "com.grappim.feature.select_info.root_presentation"
}

dependencies {
  implementation(Deps.AndroidX.viewPager2)

  implementation(project(Modules.selectInfoSelectCashbox))
  implementation(project(Modules.selectInfoSelectStock))
  implementation(project(Modules.featureSelectInfoNavigation))
}