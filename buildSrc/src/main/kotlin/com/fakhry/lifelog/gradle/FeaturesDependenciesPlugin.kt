package com.fakhry.lifelog.gradle

import Modules
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.project

class FeaturesDependenciesPlugin : Plugin<Project> {
    override fun apply(project: Project) {

        val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs")
        project.dependencies {
            add("implementation", project(Modules.Core.DATABASE))
            add("implementation", project(Modules.Core.UI))
            add("implementation", project(Modules.Core.UTILS))
            add("implementation", project(Modules.Commons.NAVIGATION))

            // UI
            add("implementation", libs.findLibrary("material").get())

            // AndroidX
            add("implementation", libs.findLibrary("androidx-core-ktx").get())
            add("implementation", libs.findLibrary("androidx-appcompat").get())

            // Lifecycles
            add("implementation", libs.findLibrary("androidx-lifecycle-viewmodel-ktx").get())
            add("implementation", libs.findLibrary("androidx-lifecycle-runtime-ktx").get())

            // Koin
            add("implementation", libs.findLibrary("koin-android").get())
        }
    }
}