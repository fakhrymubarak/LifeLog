package com.fakhry.lifelog.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class FeaturesDependenciesPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply("kotlin-android")

        val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs")
        project.dependencies {
            add("implementation", libs.findLibrary("koin-android").get())
        }
    }
}