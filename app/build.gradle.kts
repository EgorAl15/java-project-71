plugins {
    application
    jacoco

    id("io.github.ben-manes.versions") version "0.61.0"
    id("com.diffplug.spotless") version "8.9.0"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("info.picocli:picocli:4.7.7")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.20.0")

    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
    mainClass.set("hexlet.code.App")
}

spotless {
    java {
        googleJavaFormat()
    }
}

jacoco {
    toolVersion = "0.8.14"
}

tasks.test {
    useJUnitPlatform()
}

tasks.jacocoTestCoverageVerification {
    dependsOn(tasks.test)

    violationRules {
        rule {
            element = "CLASS"
            includes = listOf("hexlet.code.Differ")

            limit {
                minimum = "0.80".toBigDecimal()
            }
        }
    }
}

tasks.check {
    dependsOn(tasks.jacocoTestCoverageVerification)
}