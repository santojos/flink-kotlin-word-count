import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
}

group = "me.santojos"
version = "1.0"


repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")

    implementation("org.apache.flink:flink-java:1.7.2")
    implementation("org.apache.flink:flink-streaming-java_2.12:1.7.2")
    implementation("org.apache.flink:flink-streaming-scala_2.12:1.7.2")
    implementation("org.apache.flink:flink-metrics-prometheus_2.12:1.7.2")

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}