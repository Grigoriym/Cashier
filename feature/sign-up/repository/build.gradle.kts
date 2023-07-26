plugins {
  id(Plugins.androidLibrary)
  id(Plugins.grappimDataPlugin)
}

dependencies {
  implementation(project(Modules.featureSignUpDomain))

  implementation(project(Modules.featureAuthNetwork))

  implementation(project(Modules.commonDi))
  implementation(project(Modules.commonLce))
  implementation(project(Modules.commonAsynchronous))
}
android {
    namespace = "com.grappim.sign_up_repository"
}
