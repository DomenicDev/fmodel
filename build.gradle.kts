@Suppress("DSL_SCOPE_VIOLATION") // https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    base
    id("maven-publish")
    id("org.jetbrains.kotlinx.binary-compatibility-validator") version "0.14.0"
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotest.multiplatform) apply false
    alias(libs.plugins.dokka)
}

allprojects {
    group = "com.fraktalio.fmodel"
    version = "3.5.1"

    repositories {
        mavenCentral()
        mavenLocal()
    }
}

