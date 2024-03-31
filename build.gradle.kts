buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.9.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.51")
    }
}// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
}