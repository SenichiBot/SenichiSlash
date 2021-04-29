package me.hechfx.interaktions


import kotlinx.coroutines.runBlocking
import me.hechfx.interaktions.command.misc.*
import me.hechfx.interaktions.command.util.*
import me.hechfx.interaktions.config.Configuration
import net.perfectdreams.discordinteraktions.InteractionsServer

class Senichi(config: Configuration) {
    private val interactions = InteractionsServer(
        config.app_id.toLong(),
        config.public_key,
        config.bot_token,
        config.port
    )

    suspend fun start() {
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
        interactions.commandManager.register(
            RandomCatCommand,
            RandomCatExecutor()
        )

        interactions.start()

        interactions.commandManager.updateAllGlobalCommands(true)
    }
}