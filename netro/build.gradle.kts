@file:Suppress("ktlint")
plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "2.1.10"
    id("com.gradle.plugin-publish") version "1.3.1"
}

group = "io.github.probelalkhan"
version = "0.1"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
    }
}

gradlePlugin {
    website.set("https://github.com/probelalkhan/netro")
    vcsUrl.set("https://github.com/probelalkhan/netro")

    plugins {
        create("netro") {
            id = "io.github.probelalkhan.netro"
            implementationClass = "dev.belalkhan.netro.NetroPlugin"
            displayName = "Netro Plugin"
            description =
                "Netro is a Gradle plugin that automates the generation of Retrofit API interfaces and Kotlin data models from JSON configuration files. It streamlines API integration by handling multiple HTTP methods (GET, POST, PUT, DELETE) and enforces a structured package organization for maintainability. Built with KotlinPoet and Kotlin Serialization, Netro simplifies API development, reducing boilerplate and improving code consistency."
            tags.set(
                listOf(
                    "gradle-plugin",
                    "retrofit",
                    "api-generator",
                    "kotlin",
                    "kotlinpoet",
                    "serialization",
                    "networking",
                    "rest-api",
                    "automation",
                    "code-generation",
                ),
            )
        }
    }
}

dependencies {
    //noinspection UseTomlInstead
    implementation("com.squareup:kotlinpoet:2.1.0")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.16.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")
}
