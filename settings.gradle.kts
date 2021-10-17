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
include(":feature:select-cashbox")
include(":feature:select-stock")
include(":feature:bag")
include(":feature:payment-method")
include(":feature:sales")

include(":uikit")
include(":navigation")
include(":feature:menu")
include(":feature:products")
include(":feature:scanner")
