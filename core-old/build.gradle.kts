plugins {
    alias(libs.plugins.android.library)
}

android {
     namespace = BuildConfig.generateNamespace("core-old")
}

dependencies {
    implementation(project(Modules.STORAGE))
}