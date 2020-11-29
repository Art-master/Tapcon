package com.tapcon.game.actors.main_menu

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Batch
import com.tapcon.game.api.GameActor
import com.tapcon.game.data.Assets
import com.tapcon.game.data.Descriptors

class MusicButton(manager : AssetManager) : GameActor() {
    private val texture = manager.get(Descriptors.gameInterface)
    private var border = texture.findRegion(Assets.InterfaceAtlas.BORDER_GREEN)
    private var soundIcon = texture.findRegion(Assets.InterfaceAtlas.SOUND)

    init {
        width = border.originalWidth.toFloat() / 2
        height = border.originalHeight.toFloat() / 2
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        batch!!.color = Color.WHITE
        drawBorder(batch)
        drawPlayIcon(batch)
    }

    private fun drawBorder(batch: Batch){
        batch.draw(border, x, y, width, height)
    }

    private fun drawPlayIcon(batch: Batch){
        batch.draw(soundIcon, x, y, width, height)
    }
}