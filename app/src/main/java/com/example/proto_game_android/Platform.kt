package com.example.proto_game_android

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF

class Platform(var x: Float = 0f, var y: Float = 0f, var width: Float = 0f, var height: Float = 0f) {

    private val paint = Paint().apply {
        color = Color.DKGRAY // Set the color of the platform
        style = Paint.Style.FILL
    }

    companion object {
        fun generatePlatforms(count: Int, maxX: Float, maxY: Float): List<Platform> {
            val platforms = mutableListOf<Platform>()
            platforms.add(generateBasePlatform(maxX, maxY))
            for (i in 0 until count) {
                platforms.add(generatePlatform(maxX, maxY))
            }
            return platforms
        }

        private fun generateBasePlatform(maxX: Float, maxY: Float): Platform{
            val x = (1 * maxX).toFloat()
            val y = (1 * maxY).toFloat()
            val width = (1 * maxX).toFloat()
            val height = (1 * maxY).toFloat()
            return Platform(x, y, width, height)
        }

        private fun generatePlatform(maxX: Float, maxY: Float): Platform {
            val x = (Math.random() * maxX).toFloat()
            val y = (Math.random() * maxY).toFloat()
            val width = (Math.random() * 100 + 50).toFloat() // Ensure a minimum width of 50
            val height = (Math.random() * 50 + 20).toFloat() // Ensure a minimum height of 20
            return Platform(x, y, width, height)
        }
    }

    fun render(canvas: Canvas) {
        // Draw the platform as a rectangle
        canvas.drawRect(x, y, x + width, y + height, paint)
    }

    fun getBounds(): RectF {
        return RectF(x, y, x + width, y + height)
    }
}