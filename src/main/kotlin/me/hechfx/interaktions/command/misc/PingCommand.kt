package me.hechfx.interaktions.command.misc

import net.perfectdreams.discordinteraktions.commands.SlashCommandArguments
import net.perfectdreams.discordinteraktions.commands.SlashCommandExecutor
import net.perfectdreams.discordinteraktions.context.SlashCommandContext
import net.perfectdreams.discordinteraktions.declarations.slash.SlashCommandDeclaration
import net.perfectdreams.discordinteraktions.declarations.slash.SlashCommandExecutorDeclaration
import net.perfectdreams.discordinteraktions.declarations.slash.slashCommand

object PingCommand : SlashCommandDeclaration {
    override fun declaration() = slashCommand("ping") {
        description = "ping? pong!"
        executor = PingExecutor
    }
}

class PingExecutor : SlashCommandExecutor() {
    companion object : SlashCommandExecutorDeclaration(PingExecutor::class)

    override suspend fun execute(context: SlashCommandContext, args: SlashCommandArguments) {
        context.sendMessage {
            content = "pong!"
        }
    }
}