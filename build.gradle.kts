// Top-level build file where you can add configuration options common to all sub-projects/modules.
val appVersion = "1.0.1"

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.google.ksp) apply false
}

subprojects {
    plugins.withId("com.android.application") {
        extensions.configure<com.android.build.gradle.AppExtension> {
            defaultConfig {
                buildConfigField("String", "VERSION_NAME", "\"$appVersion\"")
            }

        }
    }

    plugins.withId("com.android.library") {
        extensions.configure<com.android.build.gradle.LibraryExtension> {
            defaultConfig {
                minSdk = 24

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                consumerProguardFiles("consumer-rules.pro")

                buildConfigField("String", "VERSION_NAME", "\"$appVersion\"")
            }

            buildFeatures {
                buildConfig = true
                viewBinding = true
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }

            buildTypes {
                release {
                    isMinifyEnabled = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }
        }
    }
}