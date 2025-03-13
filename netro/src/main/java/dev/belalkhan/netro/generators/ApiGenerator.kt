package dev.belalkhan.netro.generators

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.STRING
import com.squareup.kotlinpoet.TypeSpec
import dev.belalkhan.netro.models.Config

object ApiGenerator {
    fun generateRetrofitInterface(
        config: Config,
        packageName: String,
        apiInterfaceName: String,
    ): FileSpec {
        val apiInterface =
            TypeSpec.interfaceBuilder(apiInterfaceName)
                .addModifiers(KModifier.PUBLIC)

        config.endpoints.forEach { endpoint ->
            val functionBuilder =
                FunSpec.builder(endpoint.name)
                    .addModifiers(KModifier.ABSTRACT)
                    .addModifiers(KModifier.SUSPEND) // ✅ Make function suspend
                    .returns(TypeUtils.getType(endpoint.responseModel, packageName))

            val httpAnnotation =
                when (endpoint.method) {
                    "GET" -> "GET"
                    "POST" -> "POST"
                    "PUT" -> "PUT"
                    "DELETE" -> "DELETE"
                    else -> throw IllegalArgumentException("Unsupported HTTP method: ${endpoint.method}")
                }

            functionBuilder.addAnnotation(
                AnnotationSpec.builder(ClassName("retrofit2.http", httpAnnotation))
                    .addMember("%S", endpoint.path)
                    .build(),
            )

            val pathVariableRegex = "\\{(\\w+)}".toRegex()
            val pathVariables = pathVariableRegex.findAll(endpoint.path).map { it.groupValues[1] }.toList()

            pathVariables.forEach { pathVar ->
                functionBuilder.addParameter(
                    ParameterSpec.builder(pathVar, STRING)
                        .addAnnotation(
                            AnnotationSpec.builder(ClassName("retrofit2.http", "Path"))
                                .addMember("%S", pathVar) // ✅ Adds @Path("id") annotation
                                .build(),
                        )
                        .build(),
                )
            }

            // Add request body if needed (for POST & PUT)
            if (endpoint.method in listOf("POST", "PUT") && endpoint.requestModel != null) {
                functionBuilder.addParameter(
                    ParameterSpec.builder(
                        "body",
                        TypeUtils.getType(endpoint.requestModel, packageName),
                    ).addAnnotation(ClassName("retrofit2.http", "Body")).build(),
                )
            }

            apiInterface.addFunction(functionBuilder.build())
        }

        return FileSpec.builder(packageName, apiInterfaceName)
            .addType(apiInterface.build())
            .build()
    }
}
