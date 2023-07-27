package commons.buildTypes

import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.gradle.internal.dsl.BuildType
import org.gradle.api.NamedDomainObjectContainer
import java.io.File

object BuildTypeRelease : BuildTypes {

    override val name: String = "release"

    override fun libraryCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<BuildType>
    ): BuildType =
        namedDomainObjectContainer.getByName(name) {
            isMinifyEnabled = true
            isTestCoverageEnabled = false
        }

    override fun appCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationBuildType>,
        proguardFile: File?
    ): ApplicationBuildType =
        namedDomainObjectContainer.getByName(name) {
            isMinifyEnabled = true
            isDebuggable = false
            enableUnitTestCoverage = false
            enableAndroidTestCoverage = false

            proguardFiles(
                proguardFile!!,
                "proguard-rules.pro"
            )
        }
}