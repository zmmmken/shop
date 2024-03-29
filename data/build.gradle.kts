plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.common.android.plugin)
    alias(libs.plugins.common.hilt.plugin)
    alias(libs.plugins.common.serialization.plugin)
}

android {
    namespace = "com.kenevisi.data"
    defaultConfig {
        buildConfigField("String", "BASE_URL", "\"https://api.torob.com/\"")
    }

}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)


    //project
    implementation(projects.core)
    implementation(projects.domain)

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.okHttp.logger)

    //paging
    implementation(libs.paging)
}