import com.grappim.cashier.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidDaggerConventionPlugin :Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
            }

            dependencies {
                "implementation"(libs.findLibrary("dagger").get())
                "ksp"(libs.findLibrary("daggerCompiler").get())
                "kspAndroidTest"(libs.findLibrary("daggerCompiler").get())
                "kspTest"(libs.findLibrary("daggerCompiler").get())
            }
        }
    }
}
