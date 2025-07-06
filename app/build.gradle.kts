plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.ksp)
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
    implementation(project(Modules.Core.UI))
    implementation(project(Modules.Core.DATABASE))
    implementation(project(Modules.Commons.DATA))
    implementation(project(Modules.Features.DASHBOARD))
    // Firebase
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.analytics)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Koin
    implementation(libs.insert.koin.koin.android)

    implementation(libs.androidx.startup.runtime)
}