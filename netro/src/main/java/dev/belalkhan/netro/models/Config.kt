package dev.belalkhan.netro.models

import kotlinx.serialization.Serializable

@Serializable
data class Config(
    val baseUrl: String,
    val endpoints: List<Endpoint>,
    val models: Map<String, Map<String, String>>,
)
