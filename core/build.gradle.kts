plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.fakhry.lifelog.core"
    compileSdk = 34

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(project(Modules.STORAGE))
}