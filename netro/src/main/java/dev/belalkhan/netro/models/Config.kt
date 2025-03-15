package dev.belalkhan.netro.models

import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Config
    @JsonCreator
    constructor(
        @JsonProperty("baseUrl") val baseUrl: String,
        @JsonProperty("endpoints") val endpoints: List<Endpoint>,
        @JsonProperty("models") val models: Map<String, TypeDefinition>,
    )

data class TypeDefinition
    @JsonCreator
    constructor(
        @JsonAnySetter val properties: Map<String, Any> = mutableMapOf(),
    )
