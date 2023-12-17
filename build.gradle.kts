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
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    runtimeOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.7.3")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("tut.dushyant.aoc.Day5Kt")
}

tasks.withType<Jar> {
    manifest {
        attributes ["Main-Class"] = "tut.dushyant.aoc.Day5Kt"
    }

    from(configurations.runtimeClasspath.get().map(::zipTree))
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}