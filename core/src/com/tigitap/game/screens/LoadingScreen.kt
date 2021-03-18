package com.tigitap.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.loaders.FileHandleResolver
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.Timer
import com.tigitap.game.Prefs
import com.tigitap.game.actors.Background
import com.tigitap.game.actors.loading_progress.LoadingText
import com.tigitap.game.actors.loading_progress.ProgressBar
import com.tigitap.game.data.Descriptors
import com.tigitap.game.managers.AudioManager
import com.tigitap.game.managers.ScreenManager
import com.tigitap.game.managers.ScreenManager.Param.FIRST_APP_RUN


class LoadingScreen(params: Map<ScreenManager.Param, Any>) : GameScreen(params) {

    private var progressBar: ProgressBar? = null

    private var firstRun = false

    init {
        val prefs = Gdx.app.getPreferences(Prefs.NAME)
        firstRun = prefs.getBoolean(Prefs.FIRST_RUN, true)
        if (firstRun) prefs.putBoolean(Prefs.FIRST_RUN, false).flush()

        ScreenManager.setGlobalParameter(FIRST_APP_RUN, firstRun)

        loadStartResources()

        initProgressBarActors()
        loadResources()
        Gdx.input.inputProcessor = stage
    }

    private fun loadStartResources() {
        val resolver: FileHandleResolver = InternalFileHandleResolver()
        manager.setLoader(FreeTypeFontGenerator::class.java, FreeTypeFontGeneratorLoader(resolver))
        manager.setLoader(BitmapFont::class.java, ".ttf", FreetypeFontLoader(resolver))
        manager.load(Descriptors.background)
        manager.load(Descriptors.progressBar)
        manager.load(Descriptors.juraFont)
        manager.finishLoading()
    }

    override fun hide() {
    }

    override fun show() {
    }

    override fun render(delta: Float) {
        if (progressBar?.progress != 100)
            if (manager.update()) {
                progressBar?.progress = 100

                Timer.schedule(object : Timer.Task() {
                    override fun run() {
                        loadResourcesFinished()
                    }
                }, 0.2f)

            } else progressBar?.progress = (manager.progress * 100).toInt()

        applyStages(delta)
    }

    private fun loadResourcesFinished() {
        AudioManager.onMusicsLoaded(manager)
        AudioManager.onSoundsLoaded(manager)

        ScreenManager.setScreen(ScreenManager.Screens.MAIN_MENU_SCREEN)
    }

    private fun initProgressBarActors() {
        val background = Background(manager)

        progressBar = ProgressBar(manager)
        val loadingText = LoadingText(manager)

        val table = Table().apply {
            setFillParent(true)
            add(loadingText).padBottom(100f).align(Align.center)
            row()
            add(progressBar).width(1000f).align(Align.left)
            align(Align.center)
        }
        stageBackground.addActor(background)
        stage.addActor(table)
    }

    private fun loadResources() {
        manager.load(Descriptors.background)
        manager.load(Descriptors.icons)
        manager.load(Descriptors.gameInterface)
        loadFonts()
        AudioManager.loadMusic(manager)
        AudioManager.loadSounds(manager)
    }

    private fun loadFonts() {
        manager.load(Descriptors.scoreFont)
        manager.load(Descriptors.mainFont)
    }


    override fun pause() {
    }

    override fun resume() {
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun dispose() {
        stage.dispose()
    }
}