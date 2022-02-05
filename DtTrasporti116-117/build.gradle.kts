version = project.version
group = "org.trasporti116117"

java.sourceCompatibility = JavaVersion.VERSION_17

plugins {
    jacoco //java code coverage
    java
    id("java")
    id("pl.droidsonroids.jacoco.testkit") version "1.0.8"
    kotlin("jvm") version "1.4.10"
    application
    id("org.openjfx.javafxplugin") version "0.0.9"
    checkstyle
    pmd
    id("org.springframework.boot") version "2.6.3"
    id("io.spring.dependency-management") version "0.6.1.RELEASE"
}


allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects{
    apply(plugin = "org.openjfx.javafxplugin")
    apply(plugin = "org.gradle.jacoco")
    apply(plugin = "pl.droidsonroids.jacoco.testkit")
    apply(plugin = "org.gradle.application")
    apply(plugin = "org.gradle.java")
    apply(plugin = "checkstyle")
    apply(plugin = "pmd")
    apply(plugin = "java")
    apply(plugin = "org.springframework.boot")
    apply(plugin ="io.spring.dependency-management")

    extra["azureVersion"] = "3.13.0"

    javafx {
        version = "17.0.1"
        modules = listOf("javafx.controls", "javafx.fxml", "javafx.web")
    }

    tasks.withType<Checkstyle> {
        ignoreFailures = true
        reports {
            xml.required.set(true)
            html.required.set(true)
        }
    }

    pmd {
        toolVersion = "6.37.0"
        rulesMinimumPriority.set(4)
        ruleSets = listOf("category/java/errorprone.xml", "category/java/bestpractices.xml")
        isIgnoreFailures = true
    }

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

}
