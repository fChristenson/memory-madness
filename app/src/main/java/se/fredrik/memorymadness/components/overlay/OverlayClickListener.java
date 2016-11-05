package se.fredrik.memorymadness.components.overlay;

import android.view.View;
import android.view.View.OnClickListener;

import se.fredrik.memorymadness.common.Action;
import se.fredrik.memorymadness.common.Actions;
import se.fredrik.memorymadness.common.Store;

/**
 * Created by fredrik on 2016-11-04.
 */

public class OverlayClickListener implements OnClickListener {
    @Override
    public void onClick(View view) {
        Store.dispatch(new Action(Actions.RESET_GAME));
        view.setVisibility(View.INVISIBLE);
    }
}
