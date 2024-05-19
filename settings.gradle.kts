pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

        maven { setUrl("https://jitpack.io") }
    }
}

rootProject.name = "Cashier"

include(":app")
include(":domain")
include(":core")

include(":uikit")
include(":navigation")

include(":common:db")
include(":common:asynchronous")
include(":common:lce")
include(":common:di")
include(":common:network:serializers")
include(":common:annotations")

include(":data:network")
include(":data:repository")
include(":data:db")
include(":data:workers")

include(":utils:logger")
include(":utils:calculations")
include(":utils:date-time")
include(":utils:extensions")
include(":utils:biometric")

include(":feature:auth:domain")
include(":feature:auth:presentation")
include(":feature:auth:repository")
include(":feature:auth:network")

include(":feature:waybill:presentation")
include(":feature:waybill:domain")
include(":feature:waybill:repository")
include(":feature:waybill:network")

include(":feature:bag:presentation")
include(":feature:bag:domain")
include(":feature:bag:network")
include(":feature:bag:repository")
include(":feature:bag:db")

include(":feature:sales")
include(":feature:menu")
include(":feature:scanner")

include(":feature:payment-method:presentation")
include(":feature:payment-method:domain")

include(":feature:sign-up:presentation")
include(":feature:sign-up:domain")
include(":feature:sign-up:repository")

include(":feature:product-category:presentation")
include(":feature:product-category:domain")
include(":feature:product-category:network")
include(":feature:product-category:db")
include(":feature:product-category:repository")

include(":feature:products:network")
include(":feature:products:presentation")
include(":feature:products:domain")
include(":feature:products:repository")

include(":feature:settings")

include(":feature:select-info:select-stock")
include(":feature:select-info:select-cashbox")
include(":feature:select-info:root-presentation")
include(":feature:select-info:common-navigation")

include(":test-shared")
include(":test-shared-android")
include(":data:repository-api")
include(":data:workers-api")
