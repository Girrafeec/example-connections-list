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
rootProject.name = "example-connections-list"
include(":app")
include(":core-base")
include(":core-ui")
include(":core-components-api")
include(":core-components-impl")
include(":feature-location-api")
include(":feature-location-impl")
include(":feature-location-tracker-api")
include(":feature-location-tracker-impl")
include(":feature-connections-api")
include(":feature-connections-impl")
include(":dependency-coordinator-api")
include(":dependency-coordinator-impl")
include(":feature-connections-list")
include(":navigation-api")
include(":navigation-impl")
