plugins {
  id(Plugins.androidLibrary)
  id(Plugins.grappimDataPlugin)
}

android {
  compileOptions {
    isCoreLibraryDesugaringEnabled = true
  }
    namespace = "com.grappim.product_category.repository"
}

dependencies {
  implementation(project(Modules.dataNetwork))
  implementation(project(Modules.dataDb))
  implementation(project(Modules.utilsCalculations))
  implementation(project(Modules.utilsDateTime))

  implementation(project(Modules.commonDi))
  implementation(project(Modules.commonDb))
  implementation(project(Modules.commonLce))
  implementation(project(Modules.commonAsynchronous))

  implementation(project(Modules.featureProductCategoryDomain))
  implementation(project(Modules.featureProductCategoryDb))
  implementation(project(Modules.featureProductCategoryNetwork))

  implementation(Deps.AndroidX.paging)

  coreLibraryDesugaring(Deps.desugar)
}