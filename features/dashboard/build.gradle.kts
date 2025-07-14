plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.google.ksp)
    id(Plugins.FEATURES)
}

android {
    namespace = BuildConfig.generateNamespace("dashboard")
}

dependencies {
    implementation(project(Modules.Commons.DATA))
    implementation(project(Modules.Commons.DOMAIN))
    implementation(project(Modules.Commons.COMPONENTS))

    implementation(project(Modules.Features.SETTINGS))
    implementation(project(Modules.Features.FAVORITES))
    implementation(project(Modules.Features.CALENDAR))
    implementation(project(Modules.Features.ONBOARDING))
    implementation(project(Modules.Features.DETAILS))

    // UI
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
}