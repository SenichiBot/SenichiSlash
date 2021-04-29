package me.hechfx.interaktions.command.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.squareup.okhttp.Request
import me.hechfx.interaktions.util.HttpUtil.httpClient
import me.hechfx.interaktions.util.MessageUtil.buildReply
import net.perfectdreams.discordinteraktions.commands.SlashCommandArguments
import net.perfectdreams.discordinteraktions.commands.SlashCommandExecutor
import net.perfectdreams.discordinteraktions.context.SlashCommandContext
import net.perfectdreams.discordinteraktions.declarations.slash.SlashCommandDeclaration
import net.perfectdreams.discordinteraktions.declarations.slash.SlashCommandExecutorDeclaration
import net.perfectdreams.discordinteraktions.declarations.slash.options.CommandOptions
import net.perfectdreams.discordinteraktions.declarations.slash.slashCommand

object PokedexCommand : SlashCommandDeclaration {
    override fun declaration() = slashCommand("pokedex") {
        description = "see pokemon information"
        executor = PokedexExecutor
    }
}

class PokedexExecutor : SlashCommandExecutor() {
    companion object : SlashCommandExecutorDeclaration(PokedexExecutor::class) {
        override val options = Option

        object Option : CommandOptions() {
            val pokemon = string("pokemon", "the pokémon query")
                .register()
        }
    }

    override suspend fun execute(context: SlashCommandContext, args: SlashCommandArguments) {
        val request = Request.Builder()
            .url("https://some-random-api.ml/pokedex?pokemon=${args[options.pokemon]}")
            .get()
            .build()
        val call = httpClient.newCall(request).execute()
        val objMapper = ObjectMapper()
        val json = objMapper.readTree(call.body().string())

        val replies = listOf(
            context.buildReply("**ID:** ${json["id"].asText()}"),
            context.buildReply("**Name:** ${json["name"].asText()}"),
            context.buildReply("**Type:** ${json["type"].joinToString(", ") { it.asText() }}"),
            context.buildReply("**Abilities:** ${json["abilities"].joinToString(", ") { it.asText() }}"),
            context.buildReply("**Species:** ${json["species"].joinToString(", ") { it.asText() }}")
        )

        context.sendMessage {
            content = replies.joinToString("\n")
        }
    }
}