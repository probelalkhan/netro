package dev.belalkhan.netro.models

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Endpoint
    @JsonCreator
    constructor(
        @JsonProperty("name") val name: String,
        @JsonProperty("path") val path: String,
        @JsonProperty("method") val method: String,
        @JsonProperty("requestModel") val requestModel: String? = null,
        @JsonProperty("responseModel") val responseModel: String,
    )
