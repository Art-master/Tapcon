package com.digitap.game.services

import com.digitap.game.Config

interface ServicesController{
    fun isSignedIn(): Boolean
    fun signIn()
    fun signInSilently()
    fun signOut()
    fun submitScore(highScore: Long)
    fun showLeaderboard()
    fun getPlayerCenteredScores(callBack: CallBack)
    fun getTopScores(scoreType: Int, callBack: CallBack)
    fun unlockAchievement(achievement: Config.Achievement)
    fun incrementAchievement(achievement: Config.Achievement, value: Int)
    fun showAllAchievements()
    fun share(score: Int)
}

interface CallBack{
    fun success(list: List<String>)
    fun fail(exception: Exception, string: String)
}