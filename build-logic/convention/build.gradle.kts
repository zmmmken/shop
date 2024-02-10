import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
    id("java-gradle-plugin")
    id("org.jetbrains.kotlin.jvm").version("1.9.22")
}



java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
        allWarningsAsErrors = false
    }
}
dependencies {
    compileOnly(libs.gradleAndroid)
    compileOnly(libs.koltinGradlePlugin)
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("AndroidConfigPlugin") {
            id = "kenevisi.torob.commonAndroidConfigPlugin"
            implementationClass = "AndroidConfigPlugin"
        }
    }
    plugins {
        register("AndroidHiltConventionPlugin") {
            id = "kenevisi.torob.androidHiltConventionPlugin"
            implementationClass = "AndroidHiltConventionPlugin"
        }
    }
    plugins {
        register("TorobKotlinSerializationPlugin") {
            id = "kenevisi.torob.torobKotlinSerializationPlugin"
            implementationClass = "TorobKotlinSerializationPlugin"
        }
    }
}


