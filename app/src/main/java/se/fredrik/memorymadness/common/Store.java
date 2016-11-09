package se.fredrik.memorymadness.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import se.fredrik.memorymadness.components.card.CardReducers;

/**
 * Created by fredrik on 2016-11-03.
 */

public class Store {
    private static final Map<UUID, StoreSubscriber> subscribers = new HashMap<>();
    private static AppState state = new AppState();

    public static UUID subscribe(StoreSubscriber sub) {
        UUID id = UUID.randomUUID();
        subscribers.put(id, sub);
        return id;
    }

    public static boolean unSubscribe(UUID token) {
        StoreSubscriber removedSub = subscribers.remove(token);
        return removedSub != null;
    }

    public static AppState dispatch(Action action) {
        final AppState newState = reduce(state, action);

        for(Iterator<StoreSubscriber> i = subscribers.values().iterator(); i.hasNext();) {
            StoreSubscriber sub = i.next();
            sub.onStateUpdate(newState);
        }

        return newState;
    }

    public static AppState reduce(AppState state, Action action) {
        switch (action.getAction()) {
            case RESET_GAME:
                CardReducers.resetCards(state);
                CardReducers.resetScore(state);
                CardReducers.resetGameOver(state);
                return CardReducers.resetCombo(state);
            case CARD_CLICKED:
                return CardReducers.setCardToSelected(state, action);
            case RESET_CARDS:
                return CardReducers.resetCards(state);
            case RESET_COMBO_STREAK:
                return CardReducers.resetCombo(state);
            case SET_COMBO:
                return CardReducers.setCombo(state);
            case RESET_SCORE:
                return CardReducers.resetScore(state);
            case SET_SCORE:
                return CardReducers.addScore(state);
            case SET_CARDS_TO_UNSELECTED:
                return CardReducers.setCardsToUnselected(state);
            case RESET_IMAGE_FOR_INCORRECT_CARDS:
                return CardReducers.resetImageForIncorrectCards(state);
            case GAME_OVER:
                return CardReducers.setGameOver(state);
            case SET_MATCH_FOUND:
                return CardReducers.setMatchFound(state, action);
            default:
                return state;
        }
    }

    public static AppState getState() {
        return state;
    }
}