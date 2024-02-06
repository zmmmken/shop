package utils

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

internal fun Project.android(): LibraryExtension {
    return extensions.getByType(LibraryExtension::class.java)
}

internal fun Project.libs() = extensions.getByType<VersionCatalogsExtension>().named("libs")
