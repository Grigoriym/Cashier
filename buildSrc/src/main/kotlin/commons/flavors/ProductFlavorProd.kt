package commons.flavors

import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.gradle.internal.dsl.ProductFlavor
import org.gradle.api.NamedDomainObjectContainer

object ProductFlavorProd : BuildProductFlavor {

    override val name: String = "prod"

    override fun libraryCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<ProductFlavor>
    ): ProductFlavor =
        namedDomainObjectContainer.create(name) {
//            versionNameSuffix = "-prod"
            dimension = ConfigData.FLAVOR_ENVIRONMENT
        }

    override fun appCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationProductFlavor>
    ): ApplicationProductFlavor =
        namedDomainObjectContainer.create(name) {
//            applicationIdSuffix = ".prod"
//            versionNameSuffix = "-prod"
            dimension = ConfigData.FLAVOR_ENVIRONMENT
        }
}