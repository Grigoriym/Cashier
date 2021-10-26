package commons.flavors

import ConfigData
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.gradle.internal.dsl.ProductFlavor
import org.gradle.api.NamedDomainObjectContainer

object ProductFlavorQa : BuildProductFlavor {

    override val name: String = "qa"

    override fun libraryCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<ProductFlavor>
    ): ProductFlavor =
        namedDomainObjectContainer.create(name) {
            versionNameSuffix = "-qa"
            dimension = ConfigData.FLAVOR_ENVIRONMENT
        }

    override fun appCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationProductFlavor>
    ): ApplicationProductFlavor =
        namedDomainObjectContainer.create(name) {
            applicationIdSuffix = ".qa"
            versionNameSuffix = "-qa"
            dimension = ConfigData.FLAVOR_ENVIRONMENT
        }
}