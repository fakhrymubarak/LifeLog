plugins {
    alias(libs.plugins.android.library)
}

android {
     namespace = BuildConfig.generateNamespace("core")
}

dependencies {
    implementation(project(Modules.STORAGE))
}