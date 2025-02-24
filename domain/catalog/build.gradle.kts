plugins {
    alias(libs.plugins.application.movietime.library)
    alias(libs.plugins.application.movietime.hilt)
}

android {
    namespace = "com.application.movietime.domain.catalog"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}