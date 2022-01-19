plugins {
  id(Plugins.androidLibrary)
  id(Plugins.grappimDataPlugin)
}

android {
  compileOptions {
    isCoreLibraryDesugaringEnabled = true
  }
}

dependencies {
  implementation(project(Modules.dataNetwork))
  implementation(project(Modules.dataDb))
  implementation(project(Modules.utilsCalculations))
  implementation(project(Modules.utilsDateTime))

  implementation(project(Modules.featureSignUpDomain))

  implementation(project(Modules.commonDi))
  implementation(project(Modules.commonDb))
  implementation(project(Modules.commonLce))

  coreLibraryDesugaring(Deps.desugar)
}