# Tubes_PAM_2022
Tugas Besar Pengembangan Aplikasi Mobile Tahun Ajaran 2022

## Deskripsi Singkat
Aplikasi akan menampilkan list film dari Studio Ghibli, dengan menggunakan baseUrl : https://ghibliapi.herokuapp.com dan endpoint : https://ghibliapi.herokuapp.com/films. Aplikasi dapat menuju halaman detail yang akan menampilkan title, original title, original title romanised, description, director, producer, release date, rating score dan image.

- terdapat 3 Activity : MainActivity, DetailActivity, dan SplashActivity.
- terdapat 2 ViewModel : MainViewModel dan DetailViewModel. Kedua ViewModel ini berfungsi untuk menyimpan dan mengelola data yang ada pada User Interface Aplikasi.
- terdapat sebuah ListMovieAdapter : berfungsi untuk menampung list film dengan menggunakan RecyclerView.
- menggunakan Retrofit : sebagai networking
- menggunakan uses permission : android.permission.INTERNET

## Instalasi
- unduh file .apk yang ada
- lalu instal seperti biasa pada smartphone

## Dependensi
dependensi yang digunakan :

    implementation 'androidx.core:core-ktx:1.7.0'
    implementation 'androidx.appcompat:appcompat:1.4.1'
    implementation 'com.google.android.material:material:1.6.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    implementation 'androidx.recyclerview:recyclerview:1.2.1'
    implementation 'de.hdodenhof:circleimageview:3.1.0'
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    implementation "androidx.viewpager2:viewpager2:1.0.0"
    implementation 'com.google.code.gson:gson:2.8.6'
    implementation 'com.github.bumptech.glide:glide:4.13.0'
    implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0'
    implementation 'com.loopj.android:android-async-http:1.4.11'
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0"
    implementation 'androidx.preference:preference-ktx:1.2.0'
    implementation"org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.6.10"
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation "com.squareup.retrofit2:converter-gson:2.9.0"
    implementation "com.squareup.okhttp3:logging-interceptor:4.9.0"
