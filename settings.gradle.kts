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
include(":lint-ruleset")

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
include(":feature:bag")
include(":feature:payment-method")
include(":feature:sales")
include(":feature:menu")
include(":feature:products")
include(":feature:scanner")
include(":feature:product-category")

include(":feature:sign-up:presentation")
include(":feature:sign-up:domain")
include(":feature:sign-up:repository")

include(":feature:product-category:presentation")
include(":feature:product-category:domain")
include(":feature:product-category:network")
include(":feature:product-category:db")
include(":feature:product-category:repository")

include(":feature:select-info:select-stock")
include(":feature:select-info:select-cashbox")
include(":feature:select-info:root-presentation")
include(":feature:select-info:common-navigation")
