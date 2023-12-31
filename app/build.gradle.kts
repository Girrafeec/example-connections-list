plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
}

android {
    namespace = "com.girrafeecstud.example_connections_list"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.girrafeecstud.example_connections_list"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "0.1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {
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
        viewBinding = true
    }
}

dependencies {
    // AppCompat
    implementation(Dependencies.AndroidX.AppCompat.appCompat)

    // Core
    implementation(Dependencies.AndroidX.Core.coreKtx)

    // Unit-tests
    testImplementation(Dependencies.jUnit.jUnit)
    testImplementation(Dependencies.OkHttp3.mockWebServer)
    testImplementation(Dependencies.Coroutines.coroutinesTest)
    testImplementation(Dependencies.Mockito.mockitoKotlin)
    testImplementation(Dependencies.Mockito.mockitoInline)
    testImplementation(Dependencies.SharedPreferences.shafranSharedPreferencesMock)

    // ConstraintLayout
    implementation(Dependencies.AndroidX.Constraintlayout.constraintLayout)

    // Google Material
    implementation(Dependencies.Google.Material.material)

    // Dagger
    implementation(Dependencies.Dagger.dagger)
    kapt(Dependencies.Dagger.daggerCompiler)

    // Navigation
    implementation(Dependencies.Jetpack.Navigation.navigationFragmentKtx)
    implementation(Dependencies.Jetpack.Navigation.navigationUiKtx)

    // ViewModel
    implementation(Dependencies.Jetpack.ViewModel.viewModel)

    // Coroutines
    implementation(Dependencies.Coroutines.coroutines)

    implementation(project(":core-base"))
    implementation(project(":core-ui"))
    implementation(project(":core-components-api"))
    implementation(project(":core-components-impl"))
    implementation(project(":feature-location-api"))
    implementation(project(":feature-location-impl"))
    implementation(project(":feature-location-tracker-api"))
    implementation(project(":feature-location-tracker-impl"))
    implementation(project(":feature-connections-api"))
    implementation(project(":feature-connections-impl"))
    implementation(project(":feature-connections-list"))
//    implementation(project(":dependency-coordinator"))
    implementation(project(":navigation-api"))
//    implementation(project(":navigation-impl"))

}