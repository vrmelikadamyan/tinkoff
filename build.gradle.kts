plugins {
    id("java")
    id("io.freefair.lombok") version "8.3"
    id("org.springframework.boot") version "3.1.4"
    id("io.spring.dependency-management") version "1.1.3"
}

group = "ru.vmelik"
version = "1.0-SNAPSHOT"
java { sourceCompatibility = JavaVersion.VERSION_17 }

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.2.0")
    implementation("io.github.resilience4j:resilience4j-spring-boot3:2.1.0")

    implementation("org.mapstruct:mapstruct:1.5.5.Final")

    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.compilerArgs = listOf("-Amapstruct.defaultComponentModel=spring")
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