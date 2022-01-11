plugins {
  id(Plugins.javaLibrary)
  kotlin(Plugins.kotlinJvm)
}

dependencies {
  implementation(project(Modules.utilsLogger))
}

java {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}