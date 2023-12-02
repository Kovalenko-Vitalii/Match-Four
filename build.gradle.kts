plugins {
    kotlin("jvm") version "1.9.0"
    id("org.jetbrains.dokka") version "1.9.10"
    application
    jacoco
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation ("org.slf4j:slf4j-simple:2.0.9")
    implementation("io.github.microutils:kotlin-logging:3.0.5")
    testImplementation("junit:junit:4.13.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("junit:junit:4.13.1")
    testImplementation("junit:junit:4.13.1")
    //For Streaming to XML and JSON
    implementation("com.thoughtworks.xstream:xstream:1.4.18")
    implementation("org.codehaus.jettison:jettison:1.4.1")

    // https://mvnrepository.com/artifact/org.jetbrains.dokka/dokka-gradle-plugin
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.9.10")


}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

kotlin {
    jvmToolchain(8)
}