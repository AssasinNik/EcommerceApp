// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id("com.google.devtools.ksp") version "1.9.0-1.0.13" apply false
    alias(libs.plugins.google.gms.google.services) apply false
}
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.dagger.hilt.android.gradle.plugin)
        classpath ("com.google.gms:google-services:4.3.13")
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.0")
    }
}