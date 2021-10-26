package commons.flavors

import ConfigData
import com.android.build.gradle.internal.dsl.ProductFlavor
import com.android.build.api.dsl.ApplicationProductFlavor
import org.gradle.api.NamedDomainObjectContainer

object ProductFlavorDev : BuildProductFlavor {

    override val name: String = "dev"

    override fun libraryCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<ProductFlavor>
    ): ProductFlavor =
        namedDomainObjectContainer.create(name) {
            versionNameSuffix = "-dev"
            dimension = ConfigData.FLAVOR_ENVIRONMENT
        }

    override fun appCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationProductFlavor>
    ): ApplicationProductFlavor =
        namedDomainObjectContainer.create(name) {
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            dimension = ConfigData.FLAVOR_ENVIRONMENT
        }
}