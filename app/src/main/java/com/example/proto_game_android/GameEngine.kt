package com.example.proto_game_android

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF

class GameEngine(private val canvasWidth: Float, private val canvasHeight: Float) {

    val player: Player
    private val platforms: List<Platform>

    init {
        player = Player(100f, canvasHeight - 100f)
        player.setCanvasHeight(canvasHeight)
        player.setCanvasWidth(canvasWidth)
        platforms = generateLevel()
    }

    private fun generateLevel(): List<Platform> {
        // Example: Create a level with a ground, some floating platforms, and a high platform
        val levelPlatforms = mutableListOf<Platform>()

        // Ground platform
        levelPlatforms.add(Platform(0f, canvasHeight - 50f, canvasWidth, 50f))

        // Floating platforms
        levelPlatforms.add(Platform(200f, canvasHeight - 200f, 150f, 30f))
        levelPlatforms.add(Platform(450f, canvasHeight - 300f, 100f, 30f))
        levelPlatforms.add(Platform(700f, canvasHeight - 250f, 120f, 30f))

        // High platform
        levelPlatforms.add(Platform(900f, canvasHeight - 400f, 80f, 30f))

        // Add more platforms as needed...
        // You can also use Platform.generatePlatforms() here to add random platforms
        levelPlatforms.addAll(Platform.generatePlatforms(10, canvasWidth, canvasHeight - 100f))

        return levelPlatforms
    }

    fun checkCollision(player: Player, platform: Platform): Boolean {
        val playerRect = RectF(player.x, player.y, player.x + player.radius * 2, player.y + player.radius * 2)
        val platformRect = platform.getBounds()
        return RectF.intersects(playerRect, platformRect)
    }

    fun update() {
        player.update()
        // Handle collisions
        handleCollisions()
    }

    private fun handleCollisions() {
        for (platform in platforms) {
            if (checkCollision(player, platform)) {
                // Basic collision response: stop the player's vertical movement
                if (player.velocityY > 0) {
                    // Player is falling onto the platform
                    player.y = platform.y - player.radius * 2
                    player.velocityY = 0f
                    player.isJumping = false
                } else if (player.velocityY < 0) {
                    // Player is jumping into the bottom of the platform
                    player.y = platform.y + platform.height
                    player.velocityY = 0f
                }
            }
        }
    }

    fun render(canvas: Canvas) {
        // Clear the canvas (optional, if you want to redraw everything each frame)
        canvas.drawColor(Color.LTGRAY)

        // Render platforms
        for (platform in platforms) {
            platform.render(canvas)
        }

        // Render player
        player.render(canvas)
    }
}