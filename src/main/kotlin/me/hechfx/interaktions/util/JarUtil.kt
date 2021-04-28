package me.hechfx.interaktions.util

import me.hechfx.interaktions.boot.LoadService
import java.io.File

object JarUtil {
    fun copyFromJar(inputPath: String, outputPath: String) {
        val inputStream = LoadService::class.java.getResourceAsStream(inputPath)
        File(outputPath).writeBytes(inputStream.readAllBytes())
    }
}