@file:Suppress("UNCHECKED_CAST")

package dev.belalkhan.netro.generators

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import kotlinx.serialization.Serializable

object ModelGenerator {
    fun generateModelClass(
        name: String,
        properties: Map<String, Any>,
        packageName: String,
        isNested: Boolean = false,
    ): Pair<FileSpec, List<FileSpec>> {
        val classBuilder =
            TypeSpec.classBuilder(name)
                .addModifiers(KModifier.DATA)
                .addAnnotation(Serializable::class)

        val primaryConstructor = FunSpec.constructorBuilder()
        val nestedClasses = mutableListOf<FileSpec>()

        properties.forEach { (propName, propValue) ->
            when (propValue) {
                is String -> {
                    val isNullable = propValue.endsWith("?")
                    val cleanType = propValue.removeSuffix("?")
                    val type = TypeUtils.getType(cleanType, packageName).copy(nullable = isNullable)

                    classBuilder.addProperty(
                        PropertySpec.builder(propName, type)
                            .initializer(propName)
                            .addAnnotation(
                                AnnotationSpec.builder(ClassName("kotlinx.serialization", "SerialName"))
                                    .addMember("%S", propName)
                                    .build(),
                            )
                            .build(),
                    )
                    primaryConstructor.addParameter(propName, type)
                }

                is Map<*, *> -> {
                    val nestedClassName = propName.replaceFirstChar { it.uppercaseChar() }
                    val nestedPackage = if (isNested) packageName else "$packageName.models"

                    val nestedClassFile =
                        generateModelClass(
                            nestedClassName,
                            propValue as Map<String, Any>,
                            nestedPackage,
                            isNested = true,
                        )
                    nestedClasses.add(nestedClassFile.first)

                    val nestedType = ClassName(nestedPackage, nestedClassName)
                    classBuilder.addProperty(
                        PropertySpec.builder(propName, nestedType)
                            .initializer(propName)
                            .addAnnotation(
                                AnnotationSpec.builder(ClassName("kotlinx.serialization", "SerialName"))
                                    .addMember("%S", propName)
                                    .build(),
                            )
                            .build(),
                    )
                    primaryConstructor.addParameter(propName, nestedType)
                }
            }
        }

        classBuilder.primaryConstructor(primaryConstructor.build())

        val outputPackage = if (isNested) packageName else "$packageName.models"

        val mainClassFile =
            FileSpec.builder(outputPackage, name)
                .addType(classBuilder.build())
                .build()

        return Pair(mainClassFile, nestedClasses)
    }
}
