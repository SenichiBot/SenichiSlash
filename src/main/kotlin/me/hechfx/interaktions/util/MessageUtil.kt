package me.hechfx.interaktions.util

import net.perfectdreams.discordinteraktions.context.SlashCommandContext

object MessageUtil {
    /**
     * It sends a reply message
     * @param content your message content
     * @param prefix your message prefix
     * @return A message customized by reply
     */
    suspend fun SlashCommandContext.reply(content: String, prefix: String? = "\uD83D\uDD39") {
        this.sendMessage {
            this.content = "$prefix • $content"
        }
    }

    /**
     * It builds a reply string
     * @param content your message content
     * @param prefix your message prefix
     * @return a [String] that have build by [content] and [prefix]
     */
    fun SlashCommandContext.buildReply(content: String, prefix: String? = "\uD83D\uDD39"): String = "$prefix • $content"
}