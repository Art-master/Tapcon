package com.digitap.game.api

interface Animated {
    fun animate(type: AnimationType = AnimationType.NONE, runAfter: Runnable = Runnable { }, duration: Float = 1f)
}