pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "LifeLog - Daily Note and Mood Tracker"
include(":app")
include(":core")
include(":storage")
include(":features:settings")
include(":commons:utils")
include(":commons:resources")
include(":commons:navigation")
include(":features:settings")
include(":features:favorites")
