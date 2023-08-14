plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
}

android {
    namespace = "com.girrafeecstud.example_connections_list.connections_list"
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // AppCompat
    implementation(Dependencies.AndroidX.AppCompat.appCompat)

    // Core
    implementation(Dependencies.AndroidX.Core.coreKtx)

    // ConstraintLayout
    implementation(Dependencies.AndroidX.Constraintlayout.constraintLayout)

    // Google Material
    implementation(Dependencies.Google.Material.material)

    // Unit-tests
    testImplementation(Dependencies.jUnit.jUnit)
    testImplementation(Dependencies.Coroutines.coroutinesTest)
    testImplementation(Dependencies.Mockito.mockitoKotlin)
    testImplementation(Dependencies.Mockito.mockitoInline)

    // UI tests
    androidTestImplementation(Dependencies.jUnit.jUnitAndroidTest)
    androidTestImplementation(Dependencies.AndroidX.Espresso.espressoCore)

    // Navigation
    implementation(Dependencies.Jetpack.Navigation.navigationFragmentKtx)
    implementation(Dependencies.Jetpack.Navigation.navigationUiKtx)

    // ViewModel
    implementation(Dependencies.Jetpack.ViewModel.viewModel)

    // Coroutines
    implementation(Dependencies.Coroutines.coroutines)

    // Dagger
    implementation(Dependencies.Dagger.dagger)
    kapt(Dependencies.Dagger.daggerCompiler)

    // Orbit MVI
    implementation(Dependencies.OrbitMVI.orbitMviCore)
    implementation(Dependencies.OrbitMVI.orbitMviViewModel)

    implementation(project(":core-base"))
    implementation(project(":core-ui"))
    implementation(project(":core-components-api"))
    implementation(project(":feature-location-api"))
    implementation(project(":feature-connections-api"))
    implementation(project(":navigation-api"))
}