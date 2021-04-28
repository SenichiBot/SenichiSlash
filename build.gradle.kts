plugins {
    kotlin("jvm") version "1.4.30"
    kotlin("plugin.serialization") version "1.4.30"
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
}

val fatJar = task("fatJar", type = Jar::class) {
    println("Building fat jar for ${project.name}...")
    baseName = "${project.name}-fat"
    manifest {
        attributes["Main-Class"] = "me.hechfx.interaktions.boot.LoadService"
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    with(tasks["jar"] as CopySpec)
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    "build" {
        dependsOn(fatJar)
    }
}