package me.hechfx.interaktions

import dev.kord.common.entity.Snowflake
import me.hechfx.interaktions.command.misc.*
import me.hechfx.interaktions.command.util.*
import me.hechfx.interaktions.config.Configuration
import net.perfectdreams.discordinteraktions.InteractionsServer

class Senichi(private val config: Configuration) {
    suspend fun start() {
        val interactions = InteractionsServer(
            config.app_id.toLong(),
            config.public_key,
            config.bot_token,
            config.port
        )

        interactions.commandManager.register(
            PingCommand,
            PingExecutor()
        )
        interactions.commandManager.register(
            PongCommand,
            PongExecutor()
        )
        interactions.commandManager.register(
            MinecraftServerCommand,
            MinecraftServerExecutor()
        )
        interactions.commandManager.register(
            PokedexCommand,
            PokedexExecutor()
        )

        interactions.commandManager.updateAllGlobalCommands(true)

        interactions.start()
    }
}