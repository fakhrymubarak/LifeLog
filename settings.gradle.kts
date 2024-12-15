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
include(":commons:utils")
include(":commons:components")
include(":commons:navigation")
include(":commons:resources")
include(":features:calendar")
include(":features:favorites")
include(":features:settings")
include(":features:dashboard")
