package commons.buildTypes

import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.gradle.internal.dsl.BuildType
import org.gradle.api.NamedDomainObjectContainer
import java.io.File

interface BuildTypes {

    val name: String

    fun libraryCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<BuildType>
    ): BuildType

    fun appCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationBuildType>,
        proguardFile: File? = null
    ): ApplicationBuildType
}