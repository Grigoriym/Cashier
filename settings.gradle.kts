//pluginManagement {
//    repositories {
//        gradlePluginPortal()
//        google()
//        mavenCentral()
//    }
//}

rootProject.name = "Cashier"

include(":app")
include(":domain")

include(":data:network")
include(":data:repository")
include(":data:db")

include(":utils:logger")
include(":utils:calculations")
include(":utils:date-time")

include(":feature:auth")
include(":feature:waybill")

include(":uikit")
include(":dependencies")
