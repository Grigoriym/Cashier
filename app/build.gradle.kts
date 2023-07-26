plugins {
    id(Plugins.androidApplication)
    id(Plugins.grappimAndroidAppPlugin)
}

android {
    namespace = "com.grappim.cashier"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(Deps.AndroidX.splashScreen)
    implementation(project(Modules.utilsBiometric))

    implementation(project(Modules.featureAuthNetwork))

    implementation(project(Modules.featurePaymentMethodDomain))

    implementation(project(Modules.featureWaybillNetwork))
    implementation(project(Modules.featureWaybillDomain))
    implementation(project(Modules.featureWaybillRepository))

    implementation(project(Modules.featureBagPresentation))
    implementation(project(Modules.featureBagDomain))
    implementation(project(Modules.featureBagDb))
    implementation(project(Modules.featureBagNetwork))
    implementation(project(Modules.featureBagRepository))

    implementation(project(Modules.featureProductsDomain))
    implementation(project(Modules.featureProductsRepository))
    implementation(project(Modules.featureProductsNetwork))
}