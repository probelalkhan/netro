package dev.belalkhan.netro.generators

import com.squareup.kotlinpoet.BOOLEAN
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.INT
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.STRING
import com.squareup.kotlinpoet.TypeName

object TypeUtils {
    fun getType(type: String, packageName: String): TypeName {
        return when {
            type.startsWith("List<") -> {
                val itemType = type.removePrefix("List<").removeSuffix(">")
                ClassName("kotlin.collections", "List").parameterizedBy(getType(itemType, packageName))
            }
            type == "Int" -> INT
            type == "String" -> STRING
            type == "Boolean" -> BOOLEAN
            else -> ClassName("$packageName.models", type)
        }
    }
}