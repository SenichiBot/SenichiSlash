package me.hechfx.interaktions.command.util

import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.databind.ObjectMapper
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import me.hechfx.interaktions.util.MessageUtil.buildReply
import me.hechfx.interaktions.util.MessageUtil.reply
import net.perfectdreams.discordinteraktions.commands.SlashCommandArguments
import net.perfectdreams.discordinteraktions.commands.SlashCommandExecutor
import net.perfectdreams.discordinteraktions.context.SlashCommandContext
import net.perfectdreams.discordinteraktions.declarations.slash.SlashCommandDeclaration
import net.perfectdreams.discordinteraktions.declarations.slash.SlashCommandExecutorDeclaration
import net.perfectdreams.discordinteraktions.declarations.slash.options.CommandOptions
import net.perfectdreams.discordinteraktions.declarations.slash.slashCommand

object MinecraftServerCommand : SlashCommandDeclaration {
    override fun declaration() = slashCommand("mcserver") {
        description = "See minecraft server informations"
        executor = MinecraftServerExecutor
    }
}

class MinecraftServerExecutor : SlashCommandExecutor() {
    companion object : SlashCommandExecutorDeclaration(MinecraftServerExecutor::class) {
        override val options = Option

        object Option : CommandOptions() {
            val ip = string("ip", "minecraft server ip!")
                .register() // Don't forget to register!
        }
    }

    override suspend fun execute(context: SlashCommandContext, args: SlashCommandArguments) {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://eu.mc-api.net/v3/server/ping/${args[options.ip]}")
            .get()
            .build()
        val call = client.newCall(request).execute()
        val objMapper = ObjectMapper()
        val json = objMapper.readTree(call.body().string())
        val players = json["players"]
        val versions = json["version"]
        val onlineEmote = if (!json["online"].asBoolean()) {
            "<:dnd:831666967177592854>"
        } else {
            "<:online:831666909980131330>"
        }

        if (!json["online"].asBoolean()) {
            context.reply("O servidor está offline ou não existe!", onlineEmote)
        } else {
            val replies = listOf(
                context.buildReply("IP: ${args[options.ip]}", onlineEmote),
                context.buildReply("Players: ${players["online"]}/${players["max"]}"),
                context.buildReply("Versions: ${versions["name"]}")
            )

            context.sendMessage {
                content = replies.joinToString("\n")
            }
        }
    }
}