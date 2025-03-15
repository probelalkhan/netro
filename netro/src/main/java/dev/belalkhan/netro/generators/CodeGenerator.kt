package dev.belalkhan.netro.generators

import dev.belalkhan.netro.models.Config
import org.gradle.api.Project
import java.io.File

object CodeGenerator {
    fun generateCode(
        project: Project,
        config: Config,
        packageName: String,
        dirName: String,
    ) {
        val outputDir: File = project.layout.buildDirectory.dir("generated/netro").get().asFile
        outputDir.mkdirs()
        val fullPackage = "$packageName.$dirName"
        config.models.forEach { (modelName, typeDef) ->
            val (modelFile, nestedFiles) =
                ModelGenerator.generateModelClass(
                    modelName,
                    typeDef.properties,
                    fullPackage,
                )
            modelFile.writeTo(outputDir)
            nestedFiles.forEach { it.writeTo(outputDir) }
        }

        val apiServiceName = "${dirName.replaceFirstChar { it.uppercaseChar() }}ApiService"
        val retrofitFile =
            ApiGenerator.generateRetrofitInterface(config, fullPackage, apiServiceName)
        retrofitFile.writeTo(outputDir)

        println("Netro: Code generation complete!")
    }
}
