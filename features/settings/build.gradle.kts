plugins {
    alias(libs.plugins.android.library)
}

android {
     namespace = BuildConfig.generateNamespace("settings")
}

dependencies {
    implementation(project(Modules.Core.DATABASE))
    implementation(project(Modules.Core.UI))
    implementation(project(Modules.Commons.COMPONENTS))
    implementation(project(Modules.Commons.NAVIGATION))
    implementation(project(Modules.Core.UTILS))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}