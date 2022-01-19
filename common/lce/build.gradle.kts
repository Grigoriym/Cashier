plugins {
  id(Plugins.javaLibrary)
  kotlin(Plugins.kotlinJvm)
}

dependencies {
  implementation(project(Modules.utilsLogger))
}

java {
  sourceCompatibility = ConfigData.sourceCompatibility
  targetCompatibility = ConfigData.targetCompatibility
}