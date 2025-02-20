package com.example.proto_game_android

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Player(var x: Float, var y: Float) {
    var velocityX = 0f
    var velocityY = 0f
    val radius = 16f // Radius of the sphere (half of the previous width/height)
    val gravity = 0.5f
    var isJumping = false
    private var canvasHeight: Float = 0f
    private var canvasWidth: Float = 0f

    private val paint = Paint().apply {
        color = Color.BLUE // Set the color of the player
        style = Paint.Style.FILL
    }

    fun setCanvasHeight(canvasHeight: Float) {
        this.canvasHeight = canvasHeight
    }

    fun setCanvasWidth(canvasWidth: Float) {
        this.canvasWidth = canvasWidth
    }

    fun update() {
        velocityY += gravity

        // Update position
        x += velocityX
        y += velocityY

        // Keep the player within the screen bounds
        if (y < 0f) {
            y = 0f
            velocityY = 0f
        }
        if (y > canvasHeight - radius * 2) {
            y = canvasHeight - radius * 2
            velocityY = 0f
        }
        // Keep the player within the screen bounds (horizontal)
        if (x < 0f) {
            x = 0f
            velocityX = 0f
        }
        if (x > canvasWidth - radius * 2) {
            x = canvasWidth - radius * 2
            velocityX = 0f
        }
    }

    fun jump() {
        velocityY += -10f
    }

    fun moveLeft() {
        velocityX = -5f
    }

    fun moveRight() {
        velocityX = 5f
    }

    fun stopMoving() {
        velocityX = 0f
    }

    fun render(canvas: Canvas) {
        // Draw the player as a circle (sphere)
        canvas.drawCircle(x + radius, y + radius, radius, paint)
    }
}