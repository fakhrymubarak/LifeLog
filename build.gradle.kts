import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.google.ksp) apply false
}

subprojects {
    project.tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    // Application Module
    plugins.withId("com.android.application") {
        apply(plugin = "org.jetbrains.kotlin.android")

        extensions.configure<com.android.build.gradle.AppExtension> {
            defaultConfig {
                buildConfigField("String", "VERSION_NAME", "\"${BuildConfig.VERSION_NAME}\"")

                compileSdkVersion(BuildConfig.COMPILE_SDK)
                applicationId = BuildConfig.APP_ID
                minSdk = BuildConfig.MIN_SDK
                targetSdk = BuildConfig.TARGET_SDK
                versionCode = BuildConfig.VERSION_CODE
                versionName = BuildConfig.VERSION_NAME

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }

            signingConfigs {
                create("release") {
                    storeFile = file("${rootDir}/${project.properties["STORE_FILE"]}")
                    storePassword = project.properties["STORE_PASSWORD"] as String
                    keyAlias = project.properties["KEY_ALIAS"] as String
                    keyPassword = project.properties["KEY_PASSWORD"] as String
                }
            }

            compileOptions {
                sourceCompatibility = JavaVersion.valueOf(BuildConfig.JAVA_VERSION)
                targetCompatibility = JavaVersion.valueOf(BuildConfig.JAVA_VERSION)
            }
        }
    }

    // Library Module
    plugins.withId("com.android.library") {
        apply(plugin = "org.jetbrains.kotlin.android")
        extensions.configure<com.android.build.gradle.LibraryExtension> {
            defaultConfig {
                minSdk = BuildConfig.MIN_SDK
                compileSdk = BuildConfig.COMPILE_SDK

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                consumerProguardFiles("consumer-rules.pro")

                buildConfigField("String", "VERSION_NAME", "\"${BuildConfig.VERSION_NAME}\"")
            }

            buildFeatures {
                buildConfig = true
                viewBinding = true
            }

            compileOptions {
                sourceCompatibility = JavaVersion.valueOf(BuildConfig.JAVA_VERSION)
                targetCompatibility = JavaVersion.valueOf(BuildConfig.JAVA_VERSION)
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