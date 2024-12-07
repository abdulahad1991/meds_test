plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    /*alias(libs.plugins.hilt)
    id("kotlin-kapt")*/

    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.demo.medstest"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.demo.medstest"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
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
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)


    implementation(libs.ui)
    implementation(libs.androidx.material)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.junit.ktx)
    implementation(libs.core)
    ksp(libs.room.compiler)
    implementation(libs.androidx.room.ktx)

    implementation(libs.hilt.android.v2511)
    kapt(libs.com.google.dagger.hilt.android.compiler4)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.adapter.rxjava2)
    implementation(libs.logging.interceptor)
    implementation(libs.converter.scalars)

    implementation(libs.moshi.kotlin)
    ksp(libs.moshi.kotlin.codegen)
    implementation(libs.moshi)
    implementation(libs.converter.moshi)

    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.androidx.core.testing)

    androidTestImplementation(libs.androidx.runner)
    androidTestImplementation(libs.androidx.junit.v115)
    androidTestImplementation(libs.androidx.espresso.core.v351)

    androidTestImplementation(libs.hilt.android.testing.v248)
    kaptAndroidTest(libs.hilt.android.compiler.v248)

    testImplementation(libs.com.google.dagger.hilt.android.testing2)
    kaptTest(libs.com.google.dagger.hilt.android.compiler4)
    testAnnotationProcessor(libs.com.google.dagger.hilt.android.compiler4)

    androidTestImplementation(libs.com.google.dagger.hilt.android.testing2)
    androidTestAnnotationProcessor(libs.com.google.dagger.hilt.android.compiler4)


}