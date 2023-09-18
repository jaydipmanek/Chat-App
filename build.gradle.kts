buildscript {
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
        }
    }
        dependencies {
            classpath("com.google.gms:google-services:4.3.15")
            classpath("com.github.jakob-grabner:Circle-Progress-View:1.4")
        }
    }



// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.7.10" apply  false
}


