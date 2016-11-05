package se.fredrik.memorymadness.common;

/**
 * Created by fredrik on 2016-11-03.
 */

public interface StoreSubscriber {
    public void onStateUpdate(AppState state);
}
