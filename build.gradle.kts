// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
}

buildscript {
    val kotlin_version = "1.9.10" // Установите версию Kotlin

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.0.0") // Убедитесь, что версия плагина актуальна
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version") // Укажите версию Kotlin
    }
}
