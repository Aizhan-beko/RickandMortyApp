plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    kotlin("plugin.serialization") version "2.0.21"
}
android {
    namespace = "com.geeks.rickandmortyapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.geeks.rickandmortyapp"
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Сoil
    implementation(libs.coil.compose)


    //Material https://developer.android.com/jetpack/androidx/releases/compose-material
    implementation(libs.androidx.material)

    //Serialization https://kotlinlang.org/docs/serialization.html#formats
    implementation(libs.kotlinx.serialization.json)

    //Compose Nav https://developer.android.com/develop/ui/compose/navigation
    implementation(libs.androidx.navigation.compose)

    // Koin
    implementation(libs.koin.android)
    implementation(libs.koin.compose)

    implementation(libs.converter.gson)
    implementation(libs.squeareup.okhttp)
    implementation(libs.squeareup.logginginterceptor)

    implementation(libs.squeareup.retrofit)

    //pagination
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.paging.runtime)

    // анимации
    implementation(libs.accompanist.navigation.animation)

    implementation(libs.androidx.material.icons.extended)

    implementation("androidx.compose.foundation:foundation:1.5.0")

    // Зависимость для Compose
    implementation("androidx.compose.ui:ui:1.5.0") // или актуальная версия
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.0")
    implementation("androidx.compose.material3:material3:<compose_version>")

    implementation("com.google.accompanist:accompanist-pager:0.32.0")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.32.0")

    implementation("com.github.bumptech.glide:glide:4.15.1")
    kapt("com.github.bumptech.glide:compiler:4.15.1")

    annotationProcessor ("com.github.bumptech.glide:compiler:4.15.1")

}
