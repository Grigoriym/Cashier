plugins {
  id(Plugins.androidLibrary)
  id(Plugins.grappimAndroidLibraryPlugin)
}

android {
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = Versions.AndroidX.Compose.core
  }
}

dependencies {
  implementation(project(Modules.commonDi))
  implementation(project(Modules.commonAsynchronous))
  implementation(project(Modules.commonLce))

  implementation(project(Modules.featureSignUpDomain))
  implementation(project(Modules.featureSignUpRepository))
  implementation(project(Modules.dataNetwork))

  implementation(Deps.Compose.ui)
  implementation(Deps.Compose.material)
  implementation(Deps.Compose.toolingPreview)
  implementation(Deps.Compose.runtimeLivedata)
  implementation(Deps.Compose.foundation)
  implementation(Deps.Compose.icons)
  implementation(Deps.Compose.lifecycleViewModel)
}