plugins {
    alias(libs.plugins.android.application) // Pastikan plugin ini sudah sesuai dengan kebutuhan
}

android {
    namespace = "com.example.mymushroomf"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mymushroomf"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false // Menonaktifkan Proguard jika tidak diperlukan
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

    buildFeatures {
        viewBinding = true // Memungkinkan ViewBinding diaktifkan
    }
}

dependencies {

    // Mendeklarasikan dependensi library yang digunakan
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.preference)
    implementation(libs.play.services.maps)
    implementation(libs.retrofit)
    implementation(libs.google.gson)

    // Untuk testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Glide untuk image loading
    implementation("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")

    // RecyclerView dan CardView
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("androidx.cardview:cardview:1.0.0")

    // Gson untuk serialisasi/deserialisasi JSON
    implementation("com.google.code.gson:gson:2.8.8")

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")



}
