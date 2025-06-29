plugins {
    alias(libs.plugins.android.library)
}

android {
     namespace = BuildConfig.generateNamespace("favorites")
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    // Coroutine Lifecycle Scopes
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
}