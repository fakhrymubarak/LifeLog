plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = BuildConfig.generateNamespace("commons.data")
}

dependencies {
    implementation(project(Modules.Core.DATABASE))
}