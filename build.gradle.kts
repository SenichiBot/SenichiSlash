plugins {
    kotlin("jvm") version "1.4.30"
    kotlin("plugin.serialization") version "1.4.30"
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

group = "me.hechfx"
version = "2021-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://repo.perfectdreams.net/")
}

dependencies {
    implementation("net.perfectdreams.discordinteraktions:core:0.0.4-SNAPSHOT")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")
    implementation("com.squareup.okhttp:okhttp:2.5.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.9.9")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    shadowJar {
        manifest {
            attributes["Main-Class"] = "me.hechfx.interaktions.boot.LoadService"
        }
    }
}
