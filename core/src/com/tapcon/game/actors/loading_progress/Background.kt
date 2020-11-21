package com.tapcon.game.actors.loading_progress

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.Batch
import com.run.cookie.run.game.data.Assets
import com.badlogic.gdx.graphics.Color
import com.run.cookie.run.game.Config
import com.tapcon.game.api.GameActor
import com.tapcon.game.data.Descriptors

class Background(manager : AssetManager) : GameActor() {
    private val progressAtlas = manager.get(Descriptors.progressBar)
    private val whiteSquareRegion = progressAtlas.findRegion(Assets.ProgressAtlas.WHITE_SQUARE)

    init {
        width = Config.WIDTH_GAME
        height = Config.HEIGHT_GAME
        x = 0f
        y = 0f
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        batch!!.color = Color.WHITE
        drawBackground(batch)
    }

    private fun drawBackground(batch: Batch){
        batch.draw(whiteSquareRegion, x, y, width, height)
    }
}