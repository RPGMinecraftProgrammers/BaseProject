plugins {
    java
    `java-library`
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "dev.themeinerlp"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    maven("https://jitpack.io")
    mavenCentral()
}

var minestomVersion = "master-SNAPSHOT"

dependencies {
    api("com.github.WorldSeedGames:WorldSeedEntityEngine:v1.1.2")

    compileOnly("com.github.Minestom:Minestom:$minestomVersion")
    // implementation("de.icevizion.lib:Aves:1.1.0-SNAPSHOT")

    testImplementation("com.github.Minestom:Minestom:$minestomVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
        options.release.set(17)
    }

    jar {
        dependsOn("shadowJar")
    }

    test {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
}
