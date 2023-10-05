plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.daghlas.miniprn"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.daghlas.miniprn"
        minSdk = 22
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

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

    //mpesa key & secret reference
    android.buildFeatures.buildConfig = true
    buildTypes{
        getByName("debug") {
            buildConfigField("String", "CONSUMER_KEY", "\"JtvgHwoYwao2vgv5bCj5A9gqCGrQKFDI\"")
            buildConfigField("String", "CONSUMER_SECRET", "\"jpyAq99AiQCJT1bq\"")
        }
    }

    tasks.withType<JavaCompile>().configureEach {
        options.isFork = true
        options.forkOptions.jvmArgs = listOf(
            "--add-exports=jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED",
            "--add-exports=jdk.compiler/com.sun.tools.javac.code=ALL-UNNAMED",
            "--add-exports=jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED"
        )
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-database-ktx:20.2.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //layout sizing dependency for different screen sizes
    implementation("com.intuit.sdp:sdp-android:1.1.0")
    //qr code scanner dependency
    implementation ("com.journeyapps:zxing-android-embedded:4.3.0")
    //stk push libs
    implementation ("com.jakewharton:butterknife:10.1.0")
    annotationProcessor ("com.jakewharton:butterknife-compiler:10.1.0")
    implementation ("com.jakewharton.timber:timber:4.7.1")

    //implementation ("com.github.jumadeveloper:networkmanager:0.0.2")

    implementation ("cn.pedant.sweetalert:library:1.3")

    implementation ("com.squareup.retrofit2:retrofit:2.5.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.5.0")

    implementation ("com.squareup.okhttp3:okhttp:3.12.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:3.12.0")

    implementation ("com.google.code.gson:gson:2.8.7")

    implementation ("com.squareup.okio:okio:2.1.0")

    implementation ("javax.annotation:javax.annotation-api:1.3.2")

}