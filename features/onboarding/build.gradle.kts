plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.fakhry.lifelog.onboarding"
    compileSdk = 34

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(project(Modules.STORAGE))
    implementation(project(Modules.Commons.RESOURCES))
    implementation(project(Modules.Commons.NAVIGATION))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
}