plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = BuildConfig.generateNamespace("commons.domain")
}

dependencies {
}