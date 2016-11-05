package se.fredrik.memorymadness.components.progressBar;

import android.view.animation.Animation;
import android.widget.ProgressBar;

import se.fredrik.memorymadness.common.Action;
import se.fredrik.memorymadness.common.Actions;
import se.fredrik.memorymadness.common.Store;

/**
 * Created by fredrik on 2016-11-05.
 */

public class ProgressBarTimer implements Runnable {
    private Animation animation;
    private ProgressBar progressBar;

    public ProgressBarTimer(Animation animation, ProgressBar progressBar) {
        this.animation = animation;
        this.progressBar = progressBar;
    }

    @Override
    public void run() {
        progressBar.clearAnimation();
        Store.dispatch(new Action(Actions.GAME_OVER));
    }
}
