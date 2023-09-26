plugins {
    id("java")
    id("io.freefair.lombok") version "8.3"
}

group = "ru.vmelik"
version = "1.0-SNAPSHOT"
java { sourceCompatibility = JavaVersion.VERSION_17 }

repositories {
    mavenCentral()
}

dependencies {
}

tasks.register<Jar>("buildFatJar") {
    manifest {
        attributes["Main-Class"] = "ru.vmelik.tinkoff.Main"
    }

    archiveClassifier.set("${project.name}-fat")

    from(sourceSets.main.get().output)
    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}