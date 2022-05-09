// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath(Dependency.Google.HILT_GRADLE)
    }
}

plugins {
    id("com.android.application") version "7.1.2" apply false
    id("com.android.library") version "7.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.5.30" apply false
}

task ("clean", Delete::class) {
    delete = setOf(rootProject.buildDir)
}