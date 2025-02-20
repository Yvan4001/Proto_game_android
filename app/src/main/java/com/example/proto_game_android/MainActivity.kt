package com.example.proto_game_android

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var gameView: GameView
    private lateinit var buttonJump: Button
    private val handler = Handler(Looper.getMainLooper())
    private val updateInterval = 16L // Approximately 60 FPS

    private val updateRunnable: Runnable = object : Runnable { // Explicit type declaration
        override fun run() {
            gameView.update()
            handler.postDelayed(this, updateInterval)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameView = findViewById(R.id.gameView)
        buttonJump = findViewById(R.id.buttonJump)

        gameView.setGameLoopCallback {
            handler.post(updateRunnable)
        }

        buttonJump.setOnClickListener {
            gameView.gameEngine.player.jump()
        }
    }

    override fun onPause() {
        super.onPause()
        // Stop the game loop when the activity is paused
        handler.removeCallbacks(updateRunnable)
    }

    override fun onResume() {
        super.onResume()
        // Resume the game loop when the activity is resumed
        handler.post(updateRunnable)
    }
}