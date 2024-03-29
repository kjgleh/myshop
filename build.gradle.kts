import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.3.72"
    id("org.springframework.boot") version "2.3.5.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
    kotlin("kapt") version kotlinVersion
}

group = "me.kjgleh"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    runtimeOnly("com.h2database:h2")
    runtimeOnly("mysql:mysql-connector-java")

    implementation("au.com.console:kassava:2.1.0")

    // swagger
    implementation("io.springfox:springfox-boot-starter:3.0.0")
    compileOnly("io.springfox:springfox-swagger-ui:3.0.0")

    // querydsl
    val queryDslVersion="4.4.0"
    implementation("com.querydsl:querydsl-jpa:${queryDslVersion}")
    kapt ("com.querydsl:querydsl-apt:${queryDslVersion}:jpa")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    // jwt
    implementation("io.jsonwebtoken:jjwt:0.9.1")

    // flyway
    implementation("org.flywaydb:flyway-core")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("com.appmattus.fixture:fixture:1.0.0")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

// https://spring.io/guides/tutorials/spring-boot-kotlin/#_persistence_with_jpa
allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
}

/*
https://kotlinlang.org/docs/reference/compiler-plugins.html#no-arg-compiler-plugin
https://kotlinlang.org/docs/reference/compiler-plugins.html#jpa-support
As with the kotlin-spring plugin wrapped on top of all-open, kotlin-jpa is wrapped on top of no-arg. The plugin specifies @Entity, @Embeddable and @MappedSuperclass no-arg annotations automatically.
noArg {
    annotation("javax.persistence.Entity")
}*/
