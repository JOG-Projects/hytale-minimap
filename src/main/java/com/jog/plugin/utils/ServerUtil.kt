package com.jog.plugin.utils

import com.hypixel.hytale.server.core.universe.Universe
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

object ServerUtil {
    /*
     * Executes a task on the default world.
     */
    @JvmStatic
    fun executeWorld(task: Runnable) {
        Universe.get().defaultWorld!!.execute(task)
    }

    @JvmStatic
    fun writeArgbPng(
        pixels: IntArray,
        width: Int,
        height: Int,
        outFile: File
    ) {
        require(pixels.size == width * height) {
            "pixels.size (${pixels.size}) != width*height (${width * height})"
        }

        val img = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)

        img.setRGB(0, 0, width, height, pixels, 0, width)

        outFile.parentFile?.mkdirs()
        ImageIO.write(img, "png", outFile)
    }
}
