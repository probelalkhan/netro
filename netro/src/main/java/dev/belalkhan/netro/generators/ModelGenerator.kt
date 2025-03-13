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
        properties: Map<String, String>,
        packageName: String,
    ): FileSpec {
        val classBuilder =
            TypeSpec.classBuilder(name)
                .addModifiers(KModifier.DATA)
                .addAnnotation(Serializable::class)

        val primaryConstructor = FunSpec.constructorBuilder()
        properties.forEach { (propName, propType) ->
            val type = TypeUtils.getType(propType, packageName)
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
        classBuilder.primaryConstructor(primaryConstructor.build())

        return FileSpec.builder("$packageName.models", name)
            .addType(classBuilder.build())
            .build()
    }
}
