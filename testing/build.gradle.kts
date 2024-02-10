@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.common.android.plugin)
}

android {
    namespace = "com.kenevisi.testing"
}

dependencies {
    api(libs.junit)
    api(libs.mockk)
    api(libs.coroutine.test)
}