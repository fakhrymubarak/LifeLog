plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.fakhry.lifelog.dashboard"
    compileSdk = 34

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(project(Modules.CORE))
    implementation(project(Modules.STORAGE))
    implementation(project(Modules.Commons.COMPONENTS))
    implementation(project(Modules.Commons.RESOURCES))
    implementation(project(Modules.Commons.NAVIGATION))
    implementation(project(Modules.Commons.UTILS))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    // Coroutine Lifecycle Scopes
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
}