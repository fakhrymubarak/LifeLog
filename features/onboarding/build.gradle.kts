plugins {
    alias(libs.plugins.android.library)
}

android {
     namespace = BuildConfig.generateNamespace("onboarding")
}

dependencies {

    // TODO: PICK ONE
    implementation(project(Modules.STORAGE))
    implementation(project(Modules.Core.DATABASE))

    // TODO: PICK ONE
    implementation(project(Modules.Commons.RESOURCES))
    implementation(project(Modules.Core.UI))
    implementation(project(Modules.Commons.NAVIGATION))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
}