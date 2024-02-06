pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Torob Enterance"
include(":app")
include(":data")
include(":domain")
include(":feature")
include(":feature:product")
include(":core")
include(":designSystem")
