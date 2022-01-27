plugins {
  id(Plugins.androidLibrary)
  id(Plugins.grappimDataPlugin)
  id(Plugins.kotlinSerialization)
}

android {
  compileOptions {
    isCoreLibraryDesugaringEnabled = true
  }
}

dependencies {
  implementation(project(Modules.dataDb))
  implementation(project(Modules.utilsDateTime))
  implementation(project(Modules.commonDi))

  implementation(project(Modules.commonAsynchronous))

  api(Deps.Google.gson)
  implementation(Deps.Kotlin.serialization)

  api(Deps.okhttp)
  api(Deps.loggingInterceptor)
  api(Deps.retrofit)
  api(Deps.retrofitGsonConverter)
  api(Deps.retrofitKotlinSerializationConverter)

  debugApi(Deps.chucker)
  releaseApi(Deps.chuckerNoOp)

  coreLibraryDesugaring(Deps.desugar)
}