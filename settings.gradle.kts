pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
    resolutionStrategy {
        eachPlugin {
            // Убедитесь, что используете нужные версии плагинов для Android и Kotlin
            if (requested.id.id == "com.android.application") {
                useModule("com.android.tools.build:gradle:8.6.1")
            }
            if (requested.id.id == "org.jetbrains.kotlin.android") {
                useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
            }
        }
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
    rootProject.name = "calorie master"
    include(":app")
}