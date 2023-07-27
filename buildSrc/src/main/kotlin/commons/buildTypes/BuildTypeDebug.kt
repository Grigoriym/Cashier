package commons.buildTypes

import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.gradle.internal.dsl.BuildType
import org.gradle.api.NamedDomainObjectContainer
import java.io.File

object BuildTypeDebug : BuildTypes {

    override val name: String = "debug"

    override fun libraryCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<BuildType>
    ): BuildType =
        namedDomainObjectContainer.getByName(name) {
            isMinifyEnabled = false
            isTestCoverageEnabled = true
        }

    override fun appCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationBuildType>,
        proguardFile: File?
    ): ApplicationBuildType =
        namedDomainObjectContainer.getByName(name) {
            isMinifyEnabled = false
            isDebuggable = true
            enableUnitTestCoverage = true
            enableAndroidTestCoverage = true

            applicationIdSuffix = ".debug"
            versionNameSuffix = "-DEBUG"
        }
}