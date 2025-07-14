plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
}

kotlin {
    jvmToolchain(17) // Set a supported JDK version
}