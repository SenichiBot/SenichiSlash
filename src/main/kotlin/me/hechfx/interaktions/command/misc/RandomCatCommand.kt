package me.hechfx.interaktions.command.misc

import com.fasterxml.jackson.databind.ObjectMapper
import com.squareup.okhttp.Request
import me.hechfx.interaktions.util.HttpUtil.httpClient
import net.perfectdreams.discordinteraktions.commands.SlashCommandArguments
import net.perfectdreams.discordinteraktions.commands.SlashCommandExecutor
import net.perfectdreams.discordinteraktions.context.SlashCommandContext
import net.perfectdreams.discordinteraktions.declarations.slash.SlashCommandDeclaration
import net.perfectdreams.discordinteraktions.declarations.slash.SlashCommandExecutorDeclaration
import net.perfectdreams.discordinteraktions.declarations.slash.slashCommand

object RandomCatCommand : SlashCommandDeclaration {
    override fun declaration() = slashCommand("randomcat") {
        description = "sends a random cat image"
        executor = RandomCatExecutor
    }
}

class RandomCatExecutor : SlashCommandExecutor() {
    companion object : SlashCommandExecutorDeclaration(RandomCatExecutor::class)

    override suspend fun execute(context: SlashCommandContext, args: SlashCommandArguments) {
        val request = Request.Builder()
            .url("https://some-random-api.ml/img/cat")
            .get()
            .build()
        val call = httpClient.newCall(request).execute()
        val objMapper = ObjectMapper()
        val json = objMapper.readTree(call.body().string())

        context.sendMessage {
            embed {
                body {
                    title = "hey kitty kitty!"
                    images {
                        image = json["link"].asText()
                    }
                }
            }
        }
    }
}