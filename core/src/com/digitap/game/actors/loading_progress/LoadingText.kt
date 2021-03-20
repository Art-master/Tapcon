package com.digitap.game.actors.loading_progress

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.Batch
import com.digitap.game.api.GameActor
import com.digitap.game.data.Assets
import com.digitap.game.data.Descriptors

class LoadingText(manager: AssetManager) : GameActor() {
    private val progressAtlas = manager.get(Descriptors.progressBar)
    private val loadingText = progressAtlas.findRegion(Assets.ProgressAtlas.LOADING_TEXT)

    init {
        width = loadingText.originalWidth.toFloat()
        height = loadingText.originalHeight.toFloat()
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        color.a = parentAlpha
        batch!!.color = color

        batch.draw(loadingText, x, y, width, height)
    }
}