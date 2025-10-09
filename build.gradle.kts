plugins {
    application
    kotlin("jvm") version "2.2.0"
    kotlin("plugin.serialization") version "2.2.0"
}

group = "com.example.proucrement"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // ===== Ktor Core =====
    implementation("io.ktor:ktor-server-core:2.3.11")
    implementation("io.ktor:ktor-server-netty:2.3.11")

    // ===== Serialization (untuk @Serializable) =====
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")

    // ===== Ktor JSON & Content Negotiation =====
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.11")
    implementation("io.ktor:ktor-server-content-negotiation:2.3.11")

    // ===== Ktor Plugins Tambahan =====
    implementation("io.ktor:ktor-server-cors:2.3.11")
    implementation("io.ktor:ktor-server-call-logging:2.3.11")
    implementation("io.ktor:ktor-server-default-headers:2.3.11")
    implementation("io.ktor:ktor-server-status-pages:2.3.11")

    // 🚫 Hapus dua baris ini:
    // implementation("io.ktor:ktor-server-routing:2.3.11")
    // implementation("io.ktor:ktor-server-response:2.3.11")

    // ===== Logging =====
    implementation("ch.qos.logback:logback-classic:1.4.14")
    implementation("io.netty:netty-all:4.1.112.Final")


    // ===== Testing =====
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testImplementation("io.ktor:ktor-server-tests:2.3.11")
}


application {
    // ✅ Entry point kamu
    mainClass.set("com.example.proucrement.ApplicationKt")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    // ✅ Gunakan versi JDK yang stabil
    jvmToolchain(17)
}
