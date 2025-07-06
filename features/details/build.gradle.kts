plugins {
    alias(libs.plugins.android.library)
    id(Plugins.FEATURES)
}

android {
    namespace = BuildConfig.generateNamespace("details")
}

dependencies {
    implementation(project(Modules.Commons.DATA))
    implementation(project(Modules.Commons.DOMAIN))
    implementation(project(Modules.Commons.COMPONENTS))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}