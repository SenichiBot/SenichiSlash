package me.hechfx.interaktions.boot

import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import me.hechfx.interaktions.Senichi
import me.hechfx.interaktions.config.Configuration
import me.hechfx.interaktions.util.JarUtil.copyFromJar
import java.io.File

object LoadService {
    lateinit var senichi: Senichi

    @JvmStatic
    fun main(args: Array<String>) {

        val configFile = File("./config.json")

        if (!configFile.exists()) {
            copyFromJar("/config.json", "./config.json", this)
            throw Exception("I've not find a configuration file, i'm creating one for you!\n\nWhen you finish to write the configurations, run me again!")
        }

        val configAsString = String(configFile.readBytes())
        val config = Json {}.decodeFromString<Configuration>(configAsString)
        senichi = Senichi(config)

        runBlocking {
            senichi.start()
        }
    }
}
