package com.tapcon.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tapcon.game.services.AdsController;
import com.tapcon.game.services.CallBack;
import com.tapcon.game.services.ServicesController;
import com.tapcon.game.Config;
import com.tapcon.game.GdxGame;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Tapcon";
        config.width = 1920;
        config.height = 1080;

        AdsController controller = new Controller();
        new LwjglApplication(new GdxGame(controller), config);
    }

    static class Controller implements AdsController, ServicesController {

        @Override
        public void unlockAchievement(@NotNull Config.Achievement achievement) {

        }

        @Override
        public void incrementAchievement(@NotNull Config.Achievement achievement, int value) {

        }

        @Override
        public void showAllAchievements() {

        }

        @Override
        public boolean isWifiConnected() {
            return false;
        }

        @Override
        public boolean isInterstitialLoaded() {
            return false;
        }

        @Override
        public void showBannerAd() {

        }

        @Override
        public void hideBannerAd() {

        }

        @Override
        public void showInterstitialAd(@Nullable Runnable then) {

        }

        @Override
        public void showVideoAd(@Nullable Runnable then) {

        }

        @Override
        public void signIn() {

        }

        @Override
        public void signOut() {

        }

        @Override
        public void submitScore(long highScore) {

        }

        @Override
        public void showLeaderboard() {

        }

        @Override
        public void getPlayerCenteredScores(@NotNull CallBack callBack) {

        }

        @Override
        public void getTopScores(int scoreType, @NotNull CallBack callBack) {

        }

        @Override
        public boolean isSignedIn() {
            return false;
        }

        @Override
        public void share(int score) {

        }
    }
}
