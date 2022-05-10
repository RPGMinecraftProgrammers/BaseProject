plugins {
    java
}

group = "dev.themeinerlp"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17;
}

repositories {
    maven("https://jitpack.io")
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:annotations:23.0.0")
    implementation("com.github.Minestom:Minestom:-SNAPSHOT")
    // implementation("de.icevizion.lib:Aves:1.1.0-SNAPSHOT")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
    }
}