plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
}

android {
    namespace = "com.girrafeecstud.dependency_coordinator_impl"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {

    // Core
    implementation(Dependencies.AndroidX.Core.coreKtx)

    // Dagger
    implementation(Dependencies.Dagger.dagger)
    kapt(Dependencies.Dagger.daggerCompiler)

    // Coroutines
    implementation(Dependencies.Coroutines.coroutines)

    implementation(project(":core-base"))
    implementation(project(":dependency-coordinator-api"))
    implementation(project(":core-components-api"))
    implementation(project(":core-components-impl"))
    implementation(project(":feature-location-api"))
    implementation(project(":feature-location-impl"))
    implementation(project(":feature-connections-api"))
    implementation(project(":feature-connections-impl"))
    implementation(project(":feature-location-tracker-api"))
    implementation(project(":feature-location-tracker-impl"))
}