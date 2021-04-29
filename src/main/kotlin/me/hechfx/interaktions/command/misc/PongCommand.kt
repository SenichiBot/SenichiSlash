package me.hechfx.interaktions.command.misc

import me.hechfx.interaktions.util.MessageUtil.reply
import net.perfectdreams.discordinteraktions.commands.SlashCommandArguments
import net.perfectdreams.discordinteraktions.commands.SlashCommandExecutor
import net.perfectdreams.discordinteraktions.context.SlashCommandContext
import net.perfectdreams.discordinteraktions.declarations.slash.SlashCommandDeclaration
import net.perfectdreams.discordinteraktions.declarations.slash.SlashCommandExecutorDeclaration
import net.perfectdreams.discordinteraktions.declarations.slash.slashCommand

object PongCommand : SlashCommandDeclaration {
    override fun declaration() = slashCommand("pong") {
        description = "pong? ping!"
        executor = PongExecutor
    }
}

class PongExecutor : SlashCommandExecutor() {
    companion object : SlashCommandExecutorDeclaration(PongExecutor::class)

    override suspend fun execute(context: SlashCommandContext, args: SlashCommandArguments) {
        context.reply("ping!")
    }
}