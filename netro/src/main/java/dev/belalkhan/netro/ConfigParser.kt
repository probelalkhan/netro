package dev.belalkhan.netro

import dev.belalkhan.netro.models.Config
import kotlinx.serialization.json.Json

fun parseConfig(json: String): Config {
    return Json.decodeFromString(json)
}
