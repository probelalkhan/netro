package dev.belalkhan.netro

import dev.belalkhan.netro.generators.CodeGenerator
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

class NetroPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val extension = project.extensions.create("netroConfig", NetroExtension::class.java)
        project.tasks.register("netroSync") { task ->
            task.doLast {
                initCodeGeneration(project, extension)
            }
        }
    }

    private fun initCodeGeneration(
        project: Project,
        extension: NetroExtension,
    ) {
        val configFolder = File(extension.path)

        if (configFolder.exists() && configFolder.isDirectory) {
            val configFiles =
                configFolder.listFiles { file -> file.name.matches(Regex("^[a-zA-Z0-9_]+_config\\.yaml$")) }
            println("Netro: Found ${configFiles?.size ?: 0} valid config files in $configFolder")

            configFiles?.forEach { file ->
                val dirName = file.name.substringBefore("_config.yaml")
                try {
                    val config = loadConfig(file)
                    CodeGenerator.generateCode(project, config, extension.packageAlias, dirName)
                } catch (e: Exception) {
                    println("Netro: Error parsing ${file.name}: ${e.message}")
                }
            }
        } else {
            println("Netro: Config directory $configFolder not found.")
        }
    }
}
