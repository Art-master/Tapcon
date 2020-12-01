package com.tapcon.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.tapcon.game.Config
import com.tapcon.game.actors.Background
import com.tapcon.game.actors.game_play.Bracket
import com.tapcon.game.actors.game_play.CellIcon
import com.tapcon.game.actors.game_play.HeadIcon
import com.tapcon.game.managers.AudioManager
import com.tapcon.game.managers.ScreenManager
import com.tapcon.game.managers.VibrationManager
import com.tapcon.game.managers.VibrationManager.VibrationType.CLICK

class GamePlayScreen(params: Map<ScreenManager.Param, Any>) : GameScreen(params) {

    private val bracketLeft = Bracket(manager)
    private val bracketRight = Bracket(manager)
    private val headIcon = HeadIcon(manager)
    private val iconsGroup = Group()

    private val rowCount = 3
    private val collCount = 7

    private val icons = Array(rowCount * collCount) { CellIcon(manager) }

    private inline fun <T> Array<out T>.forEach(action: (T, Int) -> Unit): Unit {
        for ((index, element) in this.withIndex()) action(element, index)
    }

    init {
        stageBackground.addActor(Background(manager))
        initActors()

        stage.apply {
            addActor(bracketLeft)
            addActor(bracketRight)
            addActor(headIcon)
            addActor(iconsGroup)
        }
        setIconsPosition()
        setIconsGroupPosition()

        Gdx.input.inputProcessor = stage
    }

    private fun setIconsPosition() {
        var x = 0f
        var y = 0f

        icons.forEach { element, index ->
            x = if (index % collCount == 0) {
                if (index != 0) y += element.height
                0f
            } else x + element.width

            element.setPosition(x, y)
            iconsGroup.addActor(element)
        }
    }

    private fun setIconsGroupPosition() {
        val width = collCount * icons.first().width
        val height = rowCount * icons.first().height
        val x = (Config.WIDTH_GAME - width) / 2
        val y = 50f
        iconsGroup.setSize(width, height)
        iconsGroup.setPosition(x, y)
    }

    private fun initActors() {
        val headX = (Config.WIDTH_GAME - headIcon.width) / 2
        val headY = Config.HEIGHT_GAME - bracketLeft.height - 50f
        bracketLeft.setPosition(headX - headIcon.width, headY)

        headIcon.setPosition(headX, headY)

        bracketRight.scaleX = -1f
        bracketRight.setPosition(headX + (headIcon.width * 2), headY)
    }

    private fun addListenersToButtons(actor: Actor, function: () -> Unit) {
        actor.addListener(object : ClickListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                function.invoke()
                if (AudioManager.isSoundEnable) AudioManager.play(AudioManager.SoundApp.CLICK_SOUND)
                if (VibrationManager.isVibrationEnable) VibrationManager.vibrate(CLICK)
                return super.touchDown(event, x, y, pointer, button)
            }
        })
    }

    override fun hide() {

    }

    override fun show() {

    }

    override fun render(delta: Float) {
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        val bufferBitMv = if (Gdx.graphics.bufferFormat.coverageSampling) GL20.GL_COVERAGE_BUFFER_BIT_NV else 0
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT or bufferBitMv)
        applyStages(delta)
    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun resize(width: Int, height: Int) {

    }
}