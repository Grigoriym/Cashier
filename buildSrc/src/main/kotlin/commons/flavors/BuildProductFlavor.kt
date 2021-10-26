package commons.flavors

import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.gradle.internal.dsl.ProductFlavor
import org.gradle.api.NamedDomainObjectContainer

interface BuildProductFlavor {

    val name: String

    fun libraryCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<ProductFlavor>
    ): ProductFlavor

    fun appCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationProductFlavor>
    ): ApplicationProductFlavor
}