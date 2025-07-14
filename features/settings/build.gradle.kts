plugins {
    alias(libs.plugins.android.library)
    id(Plugins.FEATURES)
}

android {
     namespace = BuildConfig.generateNamespace("settings")
}

dependencies {
    implementation(project(Modules.Commons.COMPONENTS))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}