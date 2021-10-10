rootProject.name = "Cashier"

include(":app")
include(":domain")
include(":core")

include(":data:network")
include(":data:repository")
include(":data:db")
include(":data:workers")

include(":utils:logger")
include(":utils:calculations")
include(":utils:date-time")
include(":utils:extensions")

include(":feature:auth")
include(":feature:waybill")

include(":uikit")
include(":navigation")
