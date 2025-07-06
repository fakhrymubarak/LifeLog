plugins {
    alias(libs.plugins.android.library)
    id(Plugins.FEATURES)
}

android {
     namespace = BuildConfig.generateNamespace("onboarding")
}

dependencies {
}