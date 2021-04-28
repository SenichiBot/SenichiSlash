package me.hechfx.interaktions

import me.hechfx.interaktions.command.misc.PingCommand
import me.hechfx.interaktions.command.misc.PingExecutor
import me.hechfx.interaktions.config.Configuration
import net.perfectdreams.discordinteraktions.InteractionsServer

class Senichi(internal val config: Configuration) {
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

        interactions.commandManager.updateAllGlobalCommands(true)

        interactions.start()
    }
}