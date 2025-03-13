package dev.belalkhan.netro.models

import kotlinx.serialization.Serializable

@Serializable
data class Endpoint(
    val name: String,
    val path: String,
    val method: String,
    val requestModel: String? = null,
    val responseModel: String,
)
