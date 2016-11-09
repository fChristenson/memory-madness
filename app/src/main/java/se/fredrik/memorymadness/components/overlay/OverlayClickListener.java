package se.fredrik.memorymadness.components.overlay;

import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;

import se.fredrik.memorymadness.MainActivity;
import se.fredrik.memorymadness.common.Action;
import se.fredrik.memorymadness.common.Actions;
import se.fredrik.memorymadness.common.Store;
import se.fredrik.memorymadness.components.progressBar.ProgressBarAnimation;
import se.fredrik.memorymadness.components.progressBar.ProgressBarTimer;

import static android.R.attr.animation;

/**
 * Created by fredrik on 2016-11-04.
 */

public class OverlayClickListener implements OnClickListener {

    private ProgressBar progressBar;
    private Handler handler;
    private ProgressBarTimer timer;

    public OverlayClickListener(ProgressBar progressBar, Handler handler, ProgressBarTimer timer) {
        this.progressBar = progressBar;
        this.handler = handler;
        this.timer = timer;
    }

    @Override
    public void onClick(View view) {
        Store.dispatch(new Action(Actions.RESET_GAME));
        view.setVisibility(View.INVISIBLE);
        progressBar.clearAnimation();
        progressBar.setProgress(0);
        handler.removeCallbacks(timer);
    }
}
