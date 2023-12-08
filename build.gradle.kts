plugins {
    kotlin("jvm") version "1.9.21"
    application
}

group = "tut.dushyant.aoc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.slf4j:slf4j-simple:2.0.9")
    implementation("com.google.guava:guava:32.1.3-jre")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("tut.dushyant.aoc.MainKt")
}