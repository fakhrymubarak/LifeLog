plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = BuildConfig.generateNamespace("details")
}

dependencies {
    implementation(project(Modules.Core.DATABASE))
    implementation(project(Modules.Core.UI))

    implementation(project(Modules.Commons.DATA))
    implementation(project(Modules.Commons.DOMAIN))
    implementation(project(Modules.Commons.COMPONENTS))
    implementation(project(Modules.Commons.NAVIGATION))
    
    implementation(project(Modules.Core.UTILS))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Coroutine Lifecycle Scopes
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Koin
    implementation(libs.insert.koin.koin.android)
}