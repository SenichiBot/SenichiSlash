package me.hechfx.interaktions.util

import java.io.File

object JarUtil {
    fun copyFromJar(inputPath: String, outputPath: String, clazz: Any) {
        val inputStream = clazz::class.java.getResourceAsStream(inputPath)
        File(outputPath).writeBytes(inputStream.readAllBytes())
    }
}