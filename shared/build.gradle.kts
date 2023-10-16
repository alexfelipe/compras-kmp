    import com.rickclephas.kmp.nativecoroutines.gradle.ExposedSeverity.*
    import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

    plugins {
        alias(libs.plugins.kotlinMultiplatform)
        alias(libs.plugins.androidLibrary)
        id("com.google.devtools.ksp") version "1.9.10-1.0.13"
        id("com.rickclephas.kmp.nativecoroutines") version "1.0.0-ALPHA-18"
    }

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    kotlin {

        targetHierarchy.default()
        nativeCoroutines {
            exposedSeverity = ERROR
        }

        androidTarget {
            compilations.all {
                kotlinOptions {
                    jvmTarget = "1.8"
                }
            }
        }


        listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64()
        ).forEach {
            it.binaries.framework {
                baseName = "shared"
            }
        }

        sourceSets {
            val commonMain by getting {
                dependencies {
                    implementation(libs.kotlinx.datetime)
                    implementation(libs.kotlinx.coroutines.core)
                    implementation(libs.uuid)
                    //put your multiplatform dependencies here
                }
            }
            val commonTest by getting {
                dependencies {
                    implementation(libs.kotlin.test)
                }
            }
        }
    }

    android {
        namespace = "br.com.alura.compras"
        compileSdk = 34
        defaultConfig {
            minSdk = 24
        }
    }

    kotlin.sourceSets.all {
        languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
    }