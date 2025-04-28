plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android) // Убедитесь, что Kotlin плагин подключен
}

android {
    namespace = "com.example.caloriemaster"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.caloriemaster"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8" // Убедитесь, что целевая версия JVM установлена
    }
    buildFeatures {
        compose = true // Включаем Jetpack Compose
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3" // Обновленная версия компилятора Compose
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}" // Исключения для ресурсов
        }
    }
    sourceSets {
        getByName("main") {
            manifest.srcFile("app/src/main/AndroidManifest.xml")
        }
    }



    dependencies {
        val camerax_version = "1.1.0"
        val mlkit_version = "17.0.0"

        implementation("io.coil-kt:coil-compose:2.3.0")

        // CameraX и ML Kit зависимости
        implementation(libs.androidx.camera.core)
        implementation(libs.androidx.camera.camera2)
        implementation(libs.androidx.camera.lifecycle)
        implementation(libs.androidx.camera.view)
        implementation(libs.androidx.camera.extensions)
        implementation("com.google.mlkit:object-detection:$mlkit_version")


        // Jetpack Compose зависимости
        implementation(platform(libs.androidx.compose.bom)) // Подключение BOM для Compose
        implementation(libs.androidx.compose.ui) // Подключение UI библиотеки Compose через BOM
        implementation(libs.androidx.compose.foundation) // Foundation для Compose
        implementation("androidx.compose.material3:material3:1.1.0")


        // Retrofit для сетевых запросов
        implementation(libs.retrofit)
        implementation(libs.converter.gson)

        // Дополнительные зависимости
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.lifecycle.runtime.ktx)
        implementation(libs.androidx.activity.compose)

        // Тестовые зависимости
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)
        androidTestImplementation(platform(libs.androidx.compose.bom))
        androidTestImplementation(libs.androidx.ui.test.junit4)
        debugImplementation(libs.androidx.compose.ui.tooling)
        debugImplementation(libs.androidx.compose.ui.test.manifest)
    }
}
