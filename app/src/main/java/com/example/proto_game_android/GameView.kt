package com.example.proto_game_android

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class GameView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    public lateinit var gameEngine: GameEngine
    private var gameLoopStarted = false

    private lateinit var gameLoopCallback: () -> Unit // Explicit type declaration

    fun setGameLoopCallback(callback: () -> Unit) {
        gameLoopCallback = callback
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        // Initialize the game engine when the view's size is known
        gameEngine = GameEngine(w.toFloat(), h.toFloat())
        if (!gameLoopStarted) {
            gameLoopStarted = true
            gameLoopCallback()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Render the game
        gameEngine.render(canvas)
        // Invalidate to trigger a redraw
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if(this::gameEngine.isInitialized) {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Handle touch down (e.g., player jump)
                    gameEngine.player.jump()
                    return true
                }

                MotionEvent.ACTION_MOVE -> {
                    // Handle touch move (e.g., player movement)
                    if (event.x < width / 2) {
                        gameEngine.player.moveLeft()
                    } else {
                        gameEngine.player.moveRight()
                    }
                    return true
                }

                MotionEvent.ACTION_UP -> {
                    // Handle touch up (e.g., stop player movement)
                    gameEngine.player.stopMoving()
                    return true
                }
            }
            return super.onTouchEvent(event)
        }
        return false
    }

    fun update() {
        if(this::gameEngine.isInitialized) {
            gameEngine.update()
        }
    }
}