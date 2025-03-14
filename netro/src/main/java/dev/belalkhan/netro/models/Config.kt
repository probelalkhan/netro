package dev.belalkhan.netro.models

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Config
    @JsonCreator
    constructor(
        @JsonProperty("baseUrl") val baseUrl: String,
        @JsonProperty("endpoints") val endpoints: List<Endpoint>,
        @JsonProperty("models") val models: Map<String, Map<String, String>>,
    )
