plugins {
  id(Plugins.androidLibrary)
  id(Plugins.grappimDataPlugin)
  id(Plugins.kotlinParcelize)
}

dependencies {
  implementation(project(Modules.utilsCalculations))
  implementation(project(Modules.commonDi))

  implementation(Deps.Kotlin.serialization)

  api(Deps.AndroidX.roomCore)
  kapt(Deps.AndroidX.roomCompiler)
}
android {
    namespace = "com.grappim.comon.db"
}
