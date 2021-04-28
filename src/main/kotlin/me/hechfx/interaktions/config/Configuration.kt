package me.hechfx.interaktions.config

import kotlinx.serialization.Serializable

@Serializable
data class Configuration(
    val app_id: String,
    val public_key: String,
    val bot_token: String,
    val port: Int
)
