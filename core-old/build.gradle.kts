plugins {
    alias(libs.plugins.android.library)
}

android {
     namespace = BuildConfig.generateNamespace("core_old")
}

dependencies {

    // TODO: PICK ONE
    implementation(project(Modules.STORAGE))
    implementation(project(Modules.Core.DATABASE))
}