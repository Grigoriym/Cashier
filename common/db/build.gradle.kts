plugins {
  id(Plugins.androidLibrary)
  id(Plugins.grappimDataPlugin)
  id(Plugins.kotlinParcelize)
}

android {
  defaultConfig {
    kapt {
      arguments {
        arg("room.incremental", "true")
        arg("room.expandProjection", "true")
      }
    }
  }
}

dependencies {
  implementation(project(Modules.utilsCalculations))
  implementation(project(Modules.commonDi))

  implementation(Deps.Kotlin.serialization)

  api(Deps.AndroidX.roomCore)
  kapt(Deps.AndroidX.roomCompiler)
}