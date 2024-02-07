import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import utils.libs

class TorobKotlinSerializationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("kotlinx-serialization")

            dependencies {
                "implementation"(libs().findLibrary("kotlin.serializable.json").get())
                "implementation"(libs().findLibrary("kotlin.serializable.convertor").get())
            }
        }
    }
}