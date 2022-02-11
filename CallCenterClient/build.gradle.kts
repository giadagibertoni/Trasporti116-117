plugins {
    java
    application
    id("org.openjfx.javafxplugin") version "0.0.9"
}

group = "org.GiadaGibertoni"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:annotations:20.1.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    implementation("com.sothawo:mapjfx:2.15.3")

    implementation("org.apache.clerezza.ext:org.json.simple:0.4")
    implementation("ca.uhn.hapi.fhir:hapi-fhir:5.6.2")
    implementation("ca.uhn.hapi.fhir:hapi-fhir-base:5.6.2")
    implementation("ca.uhn.hapi.fhir:hapi-fhir-structures-r4:5.6.2")
}

javafx {
    version = "15.0.1"
    modules = listOf("javafx.controls", "javafx.fxml", "javafx.web")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}