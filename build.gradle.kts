plugins {
    id("com.android.application") version "8.6.1"
    id("org.jetbrains.kotlin.android") version "2.1.0"
}
android {
    namespace = "com.example.caloriemaster"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.caloriemaster"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("io.coil-kt:coil-compose:2.1.0")
    implementation("androidx.compose.material3:material3:1.3.1")

    // Camera dependencies
    implementation("androidx.camera:camera-camera2:1.4.0")
    implementation("androidx.camera:camera-core:1.4.0")
    implementation("androidx.camera:camera-extensions:1.4.0")
    implementation("androidx.camera:camera-lifecycle:1.4.0")
    implementation("androidx.camera:camera-view:1.4.0")

    // Core libraries
    implementation("androidx.core:core-ktx:1.15.0")

    // Gson converter for Retrofit
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Testing libraries
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")

    // Compose libraries
    implementation("androidx.compose.ui:ui:1.7.5")
    implementation("androidx.compose.ui:ui-graphics:1.7.5")
    implementation("androidx.compose.ui:ui-tooling:1.5.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.0")
    implementation("androidx.compose.material3:material3:1.3.1")
    implementation("androidx.compose.foundation:foundation:1.5.0")
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // Activity and Compose integration
    implementation("androidx.activity:activity-compose:1.9.3")
}
