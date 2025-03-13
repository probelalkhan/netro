package dev.belalkhan.netro

import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import javax.inject.Inject

abstract class NetroExtension
    @Inject
    constructor(objects: ObjectFactory) {
        private val configDir: Property<String> = objects.property(String::class.java)
        private val packageName: Property<String> = objects.property(String::class.java)

        var path: String
            @JvmSynthetic get() = configDir.get()

            @JvmSynthetic set(value) = configDir.set(value)

        var packageAlias: String
            @JvmSynthetic get() = packageName.get()

            @JvmSynthetic set(value) = packageName.set(value)
    }
