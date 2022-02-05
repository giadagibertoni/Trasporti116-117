plugins {
    jacoco //java code coverage
    java
    id("java")
    id("pl.droidsonroids.jacoco.testkit") version "1.0.8"
    kotlin("jvm") version "1.4.10"
    id("org.springframework.boot") version "2.6.3"
    id("io.spring.dependency-management") version "0.6.1.RELEASE"
}

dependencies {
    implementation("com.azure:azure-digitaltwins-core:1.0.3")
    implementation("com.azure:azure-identity:1.2.4")
    implementation("com.microsoft.azure.sdk.iot:iot-service-client:1.6.23")

    implementation("org.apache.clerezza.ext:org.json.simple:0.4")

    implementation("ca.uhn.hapi.fhir:hapi-fhir:5.6.2")
    implementation("ca.uhn.hapi.fhir:hapi-fhir-base:5.6.2")
    implementation("ca.uhn.hapi.fhir:hapi-fhir-structures-r4:5.6.2")

    testImplementation("junit:junit:4.13")

    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

repositories {
    mavenCentral()
}

version = project.version

extra["azureVersion"] = "3.13.0"

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>{
    kotlinOptions.allWarningsAsErrors = true
}

buildscript {
    repositories {
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath("gradle.plugin.com.github.jengelman.gradle.plugins:shadow:7.0.0")
    }
}

tasks.withType<Test> {
    useJUnit()
}


