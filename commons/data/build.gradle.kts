plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.google.ksp)
}

android {
    namespace = BuildConfig.generateNamespace("commons.data")
}

dependencies {
    implementation(project(Modules.Core.DATABASE))

    // Koin
    implementation(libs.insert.koin.koin.android)
}