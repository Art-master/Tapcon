package com.tapcon.game;

import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;
import com.run.cookie.run.game.services.AdsController;
import com.run.cookie.run.game.services.CallBack;
import com.run.cookie.run.game.services.ServicesController;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.UIApplication;

public class IOSLauncher extends IOSApplication.Delegate implements AdsController, ServicesController {
    @Override
    protected IOSApplication createApplication() {
        IOSApplicationConfiguration config = new IOSApplicationConfiguration();
        return new IOSApplication(new GdxGame(this), config);
    }

    public static void main(String[] argv) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(argv, null, IOSLauncher.class);
        pool.close();
    }

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