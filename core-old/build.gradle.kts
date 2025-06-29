plugins {
    alias(libs.plugins.android.library)
}

android {
     namespace = BuildConfig.generateNamespace("core_old")
}

dependencies {
    implementation(project(Modules.STORAGE))
}