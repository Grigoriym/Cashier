plugins {
  id(Plugins.javaLibrary)
  kotlin(Plugins.kotlinJvm)
  kotlin(Plugins.kotlinKapt)
}

dependencies {
  implementation(Deps.Google.dagger)
  kapt(Deps.Google.daggerCompiler)
}

java {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}