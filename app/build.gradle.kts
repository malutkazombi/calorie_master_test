plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android) // Убедитесь, что Kotlin плагин подключен
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 35
    buildFeatures {
        compose = true
    }

    defaultConfig {
        applicationId = "com.example.myapplication"
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
        sourceCompatibility = JavaVersion.VERSION_19
        targetCompatibility = JavaVersion.VERSION_19
    }

    kotlinOptions {
        jvmTarget = "19" // Убедитесь, что целевая версия JVM установлена
    }

    buildFeatures {
        compose = true // Включаем Jetpack Compose
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
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
}


dependencies {
    val camerax_version = "1.1.0"
    val mlkit_version = "17.0.0"

        implementation ("com.squareup.retrofit2:retrofit:2.9.0")
        implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
        implementation ("com.google.accompanist:accompanist-permissions:0.26.3-beta")
        implementation ("androidx.camera:camera-core:1.1.0(")
        implementation ("androidx.camera:camera-lifecycle:1.1.0")
        implementation ("androidx.camera:camera-view:1.0.0(")
        implementation ("com.google.mlkit:barcode-scanning:17.0.0")


    implementation("io.coil-kt:coil-compose:2.3.0")
    implementation("com.google.android.material:material:1.8.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.google.accompanist:accompanist-permissions:0.28.0")  // Версия может измениться, проверьте актуальную на момент добавления

    // Корутинный обработчик для асинхронных запросов
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // CameraX и ML Kit зависимости
    implementation("androidx.camera:camera-core:$camerax_version")
    implementation("androidx.camera:camera-camera2:$camerax_version")
    implementation("androidx.camera:camera-lifecycle:$camerax_version")
    implementation("androidx.camera:camera-view:$camerax_version") // Убедитесь, что эта версия совместима
    implementation("com.google.mlkit:object-detection:$mlkit_version")
    implementation("com.google.mlkit:barcode-scanning:17.0.0")
    implementation("com.google.mlkit:common:16.1.1")
    implementation("com.github.furkanturkn:camerax-mlkit-pack:1.1.2")

    // Jetpack Compose зависимости
    implementation(platform(libs.androidx.compose.bom)) // Подключение BOM для Compose
    implementation(libs.androidx.compose.ui) // Подключение UI библиотеки Compose через BOM
    implementation(libs.androidx.compose.foundation) // Foundation для Compose
    implementation ("androidx.compose.material3:material3:1.1.0") // Для использования Material3
    implementation ("androidx.compose.ui:ui:1.5.0")// Актуальная версия для Compose


    // Дополнительные зависимости
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation("androidx.compose.material:material-icons-extended:1.5.0") // Для расширенных иконок
    implementation("androidx.compose.material3:material3:1.2.0")
    // Тестовые зависимости
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
