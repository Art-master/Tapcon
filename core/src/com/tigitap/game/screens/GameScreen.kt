package com.tigitap.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.badlogic.gdx.utils.viewport.FitViewport
import com.tigitap.game.Config
import com.tigitap.game.Prefs
import com.tigitap.game.api.Animated
import com.tigitap.game.api.AnimationType
import com.tigitap.game.managers.ScreenManager
import com.tigitap.game.managers.ScreenManager.Param.ASSET_MANAGER
import com.tigitap.game.managers.ScreenManager.Param.SERVICES_CONTROLLER
import com.tigitap.game.services.AdsController
import com.tigitap.game.services.ServicesController

abstract class GameScreen(params: Map<ScreenManager.Param, Any>) : Screen, Animated {
    val manager = params[ASSET_MANAGER] as AssetManager
    val adsController = params[SERVICES_CONTROLLER] as AdsController
    val servicesController = params[SERVICES_CONTROLLER] as ServicesController

    private val camera = OrthographicCamera(Config.WIDTH_GAME, Config.HEIGHT_GAME)
    var stageBackground = Stage(ExtendViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat(), camera))
    var stage = Stage(FitViewport(Config.WIDTH_GAME, Config.HEIGHT_GAME, camera))

    val prefs = Gdx.app.getPreferences(Prefs.NAME)!!

    fun applyStages(delta: Float) {
        stageBackground.viewport.apply()
        stageBackground.act(delta)
        stageBackground.draw()

        stage.viewport.apply()
        stage.act(delta)
        stage.draw()
    }

    override fun animate(type: AnimationType, runAfter: Runnable, duration: Float) {
        if (type == AnimationType.SCENE_TRANSFER)
            stage.addAction(
                    Actions.sequence(
                            Actions.repeat(3, Actions.sequence(
                                    Actions.alpha(1f, 0.00f),
                                    Actions.alpha(0f, 0.2f),
                                    Actions.delay(0.2f))),
                            Actions.run(runAfter)))
    }

    override fun dispose() {
        stageBackground.dispose()
        stage.dispose()
        //stageShadow.dispose() // Early disposing lead to blink
    }
}