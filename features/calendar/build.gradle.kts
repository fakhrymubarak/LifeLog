plugins {
    alias(libs.plugins.android.library)
    id(Plugins.FEATURES)
}

android {
     namespace = BuildConfig.generateNamespace("calendar")
}

dependencies {
    implementation(project(Modules.Commons.DATA))
    implementation(project(Modules.Commons.DOMAIN))
    implementation(project(Modules.Commons.COMPONENTS))
}