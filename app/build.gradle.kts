plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.testing.projek_raymond"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.testing.projek_raymond"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    // Mengaktifkan fitur ViewBinding agar elemen XML layout bisa diakses langsung via binding tanpa findViewById
    buildFeatures{
        viewBinding = true
    }
}

dependencies {
    // AndroidX & Komponen Dasar UI
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat) // Menyediakan dukungan AppCompatActivity, Toolbar, dan ActionBar
    implementation(libs.androidx.constraintlayout) // Layout berbasis constraint untuk susunan view fleksibel
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // Google Material Components (Digunakan untuk: Snackbar, BottomNavigationView, ChipGroup, MaterialCardView, TextInputLayout)
    implementation(libs.material)

    // Library Glide: Digunakan untuk memuat gambar dari URL secara asinkron (misal: avatar profil di modul Komunitas)
    implementation("com.github.bumptech.glide:glide:4.16.0")

    // Library GridLayout: Untuk tata letak produk dalam format grid di halaman katalog
    implementation("androidx.gridlayout:gridlayout:1.0.0")

    // Library ViewPager2: Untuk membuat slide halaman geser (digunakan pada onboarding/tutorial di TutorialActivity)
    implementation("androidx.viewpager2:viewpager2:1.1.0")

    // Library DotsIndicator: Indikator titik visual (WormDotsIndicator) yang tersinkron dengan slide ViewPager2
    implementation("com.tbuonomo:dotsindicator:5.1.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}