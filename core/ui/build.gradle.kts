plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = BuildConfig.generateNamespace("core.ui")
}

dependencies {
    implementation(libs.material)
}