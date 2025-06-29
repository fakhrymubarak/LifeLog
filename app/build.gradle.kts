plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id(libs.plugins.google.ksp.get().pluginId)
}

android {
    namespace = BuildConfig.APP_ID

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
        }
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    // TODO: PICK ONE
    implementation(project(Modules.Commons.RESOURCES))
    implementation(project(Modules.Core.UI))
    implementation(project(Modules.Features.DASHBOARD))
    // Firebase
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.analytics)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Koin core
    implementation(libs.insert.koin.koin.android)
    ksp(libs.koin.ksp.compiler)   // KSP code gen

    implementation(libs.androidx.startup.runtime)
}