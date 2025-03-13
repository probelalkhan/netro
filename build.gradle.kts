// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    // Core Plugins
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false

    // Dependency Injection
    alias(libs.plugins.hilt) apply false

    // Code Generation
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.kotlinx.serialization) apply false

    // Code Quality
    alias(libs.plugins.ktlint)
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
}

ktlint {
    android.set(true)
    ignoreFailures.set(false)
    verbose.set(true)
    outputToConsole.set(true)

    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
    }

    filter {
        exclude("**/generated/**") // Avoid formatting generated files
    }
}
