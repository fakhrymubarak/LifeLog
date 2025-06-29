plugins {
    alias(libs.plugins.android.library)
}

android {
     namespace = BuildConfig.generateNamespace("dashboard")
}

dependencies {
    implementation(project(Modules.CORE_OLD))

    // TODO: PICK ONE
    implementation(project(Modules.STORAGE))
    implementation(project(Modules.Core.DATABASE))
    implementation(project(Modules.Commons.COMPONENTS))

    // TODO: PICK ONE
    implementation(project(Modules.Commons.RESOURCES))
    implementation(project(Modules.Core.UI))
    implementation(project(Modules.Commons.NAVIGATION))
    // TODO: PICK ONE
    implementation(project(Modules.Commons.UTILS))
    implementation(project(Modules.Core.UTILS))
    implementation(project(Modules.Features.SETTINGS))
    implementation(project(Modules.Features.FAVORITES))
    implementation(project(Modules.Features.CALENDAR))
    implementation(project(Modules.Features.ONBOARDING))
    implementation(project(Modules.Features.DETAILS))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    // UI
    implementation(libs.material)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // Coroutine Lifecycle Scopes
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
}