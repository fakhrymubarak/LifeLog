plugins {
    alias(libs.plugins.android.library)
}

android {
     namespace = BuildConfig.generateNamespace("resources")
}

dependencies {
    implementation(libs.material)
}