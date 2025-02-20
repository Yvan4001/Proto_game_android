# Proto Game Android

**A Simple 2D Platformer Game for Android**

![Game Screenshot](path/to/your/screenshot.png)  <!-- Replace with a screenshot of your game -->

## Table of Contents

-   [Introduction](#introduction)
-   [Features](#features)
-   [Getting Started](#getting-started)
    -   [Prerequisites](#prerequisites)
    -   [Installation](#installation)
    -   [Running the Game](#running-the-game)
-   [Game Controls](#game-controls)
-   [Project Structure](#project-structure)
-   [Code Highlights](#code-highlights)
    -   [Game Loop](#game-loop)
    -   [Player Movement and Physics](#player-movement-and-physics)
    -   [Collision Detection](#collision-detection)
    -   [Rendering](#rendering)

## Introduction

This project is a basic 2D platformer game developed for Android using Kotlin and the Android SDK. It demonstrates fundamental game development concepts such as:

-   Game loop management
-   Player movement and physics
-   Collision detection between the player and platforms
-   Rendering of game elements
-   Basic user interface elements

The game is designed to be a starting point for learning Android game development and can be extended with more advanced features.

## Features

-   **Player Movement:** The player can move left and right.
-   **Jumping:** The player can jump using a button.
-   **Gravity:** The player is affected by gravity.
-   **Platform Collision:** The player can collide with platforms.
-   **Screen Boundaries:** The player cannot move off the edges of the screen.
-   **Basic Rendering:** The game renders the player and platforms.
- **Game Loop:** The game has a game loop that updates and renders the game state.

## Getting Started

### Prerequisites

-   **Android Studio:** You need Android Studio installed on your machine. You can download it from [https://developer.android.com/studio](https://developer.android.com/studio).
-   **Android SDK:** Make sure you have the Android SDK installed and configured in Android Studio.
-   **Emulator or Device:** You'll need an Android emulator or a physical Android device to run the game.

### Installation

1.  **Clone the Repository:**
2. (Replace `https://github.com/Yvan4001/Proto_game_android` with the actual URL of your Git repository.)
2.  **Open in Android Studio:**
    -   Open Android Studio.
    -   Click "Open" and select the directory where you cloned the repository.
3.  **Sync Project:**
    -   Wait for Android Studio to sync the project with Gradle.

### Running the Game

1.  **Connect Device or Start Emulator:**
    -   Connect your Android device to your computer or start an Android emulator.
2.  **Run the App:**
    -   Click the "Run" button (green play icon) in Android Studio.
    -   Select your device or emulator from the list.

## Game Controls

-   **Jump:** Tap the "Jump" button at the bottom of the screen.
-   **Move Left:** Touch the left side of the screen.
-   **Move Right:** Touch the right side of the screen.

## Code Highlights

### Game Loop

The game loop is implemented in `MainActivity.kt` using a `Handler` and a `Runnable`:
kotlin private val updateRunnable: Runnable = object : Runnable { override fun run() { gameView.update() handler.postDelayed( this,  updateInterval) } }

### Player Movement and Physics

The `Player.kt` class handles player movement and physics:
kotlin class Player(var x: Float, var y: Float) { // ... fun update() { velocityY += gravity x += velocityX y += velocityY // ... boundary checks ... } // ... }

### Collision Detection

Collision detection between the player and platforms is implemented in `GameEngine.kt`:
kotlin fun checkCollision(player:  Player, platform: Platform): Boolean { val playerRect = RectF(player.x, player.y, player.x + player.radius * 2, player.y + player.radius * 2) val platformRect = platform.getBounds()  return RectF.intersects(playerRect,  platformRect) }

### Rendering

The `GameView.kt` class handles the rendering of the game:
kotlin override fun onDraw(canvas: Canvas) { super.onDraw(canvas)  if(this::gameEngine. isInitialized)  { gameEngine.render(canvas)  } invalidate() }