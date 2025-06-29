plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = BuildConfig.generateNamespace("core.resources")
}

dependencies {
    implementation(libs.material)
}