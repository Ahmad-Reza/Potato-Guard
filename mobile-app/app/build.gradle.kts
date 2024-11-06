plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.potatoguard"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.potatoguard"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    implementation("androidx.startup:startup-runtime:1.2.0")
//    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")

    // Retrofit for network calls
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // ViewModel and LiveData for MVVM
//    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
//    implementation("androidx.lifecycle:lifecycle-viewmodel:2.8.7")
//    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")

    implementation("androidx.lifecycle:lifecycle-viewmodel:2.6.1") // or the latest version
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1") // for ViewModel with Kotlin support
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")

    implementation("androidx.cardview:cardview:1.0.0")
    implementation("com.squareup.picasso:picasso:2.8")
}