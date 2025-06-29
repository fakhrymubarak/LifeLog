plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.google.ksp)
}

android {
    namespace = BuildConfig.generateNamespace("core.storage")
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // ROOM database
    api(libs.androidx.room.runtime)
    api(libs.room.ktx)
    ksp(libs.androidx.room.compiler)

    // Koin core
    implementation(libs.insert.koin.koin.android)
    ksp(libs.koin.ksp.compiler)   // KSP code gen
}