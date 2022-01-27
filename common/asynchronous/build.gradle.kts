plugins {
  id(Plugins.javaLibrary)
  kotlin(Plugins.kotlinJvm)
  kotlin(Plugins.kotlinKapt)
  id(Plugins.kotlinSerialization)
}

dependencies {
  implementation(project(Modules.utilsLogger))
  implementation(project(Modules.commonDi))
  implementation(project(Modules.commonLce))

  implementation(Deps.Kotlin.coroutinesCore)

  implementation(Deps.Google.dagger)
  kapt(Deps.Google.daggerCompiler)

  implementation(Deps.Kotlin.serialization)
}

java {
  sourceCompatibility = ConfigData.sourceCompatibility
  targetCompatibility = ConfigData.targetCompatibility
}