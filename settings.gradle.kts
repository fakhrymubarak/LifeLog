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
include(":core-old")
include(":core:database")
include(":core:ui")
include(":core:utils")
include(":commons:data")
include(":commons:domain")
include(":commons:components")
include(":commons:navigation")
include(":features:calendar")
include(":features:favorites")
include(":features:settings")
include(":features:dashboard")
include(":features:onboarding")
include(":features:details")
