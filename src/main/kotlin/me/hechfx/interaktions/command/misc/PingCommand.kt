package me.hechfx.interaktions.command.misc

import me.hechfx.interaktions.util.MessageUtil.buildReply
import me.hechfx.interaktions.util.MessageUtil.reply
import net.perfectdreams.discordinteraktions.commands.SlashCommandArguments
import net.perfectdreams.discordinteraktions.commands.SlashCommandExecutor
import net.perfectdreams.discordinteraktions.context.SlashCommandContext
import net.perfectdreams.discordinteraktions.declarations.slash.SlashCommandDeclaration
import net.perfectdreams.discordinteraktions.declarations.slash.SlashCommandExecutorDeclaration
import net.perfectdreams.discordinteraktions.declarations.slash.options.CommandOptions
import net.perfectdreams.discordinteraktions.declarations.slash.slashCommand

object PingCommand : SlashCommandDeclaration {
    override fun declaration() = slashCommand("ping") {
        description = "ping? pong!"
        executor = PingExecutor
    }
}

class PingExecutor : SlashCommandExecutor() {
    companion object : SlashCommandExecutorDeclaration(PingExecutor::class) {
        override val options = Option

        object Option : CommandOptions() {
            val ephemeral = boolean("ephemeral", "send the message as ephemeral")
                .register() // Don't forget to register!
        }
    }
    override suspend fun execute(context: SlashCommandContext, args: SlashCommandArguments) {
        if (args[options.ephemeral]) {
            context.sendEphemeralMessage {
                content = context.buildReply("pong!")
            }
        } else {
            context.reply("pong!")
        }
    }
}