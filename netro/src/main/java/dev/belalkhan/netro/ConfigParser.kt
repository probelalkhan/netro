package dev.belalkhan.netro

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import dev.belalkhan.netro.models.Config
import java.io.File

fun loadConfig(file: File): Config {
    val mapper = ObjectMapper(YAMLFactory())
    return mapper.readValue(file, Config::class.java)
}
