package se.fredrik.memorymadness.components.card;

import java.util.ListIterator;
import java.util.UUID;

import se.fredrik.memorymadness.R;
import se.fredrik.memorymadness.common.AppState;
import se.fredrik.memorymadness.common.Action;
import se.fredrik.memorymadness.common.Payloads;

/**
 * Created by fredrik on 2016-11-04.
 */

public class CardReducers {
    private static final int SCORE_UNIT = 100;

    public static AppState setCardToSelected(AppState state, Action action) {
        UUID selectedCardId = (UUID) action.getPayload().get(Payloads.SELECTED_CARD_ID);

        for(ListIterator<Card> i = state.getCards().listIterator(); i.hasNext();) {
            Card card = i.next();
            if (card.getId() == selectedCardId) {
                card.setSelected(true);
                card.setImageId(R.mipmap.card_active);
            }
        }

        return state;
    }

    public static AppState resetCards(AppState state) {
        for(ListIterator<Card> i = state.getCards().listIterator(); i.hasNext();) {
            Card card = i.next();
            card.setImageId(R.mipmap.card);
            card.setSelected(false);
        }

        return state;
    }

    public static AppState addScore(AppState state) {
        state.setScore(state.getScore() + (SCORE_UNIT * state.getCombo()));
        return state;
    }

    public static AppState resetScore(AppState state) {
        state.setScore(0);
        return state;
    }

    public static AppState setCardsToUnselected(AppState state) {
        for(ListIterator<Card> i = state.getCards().listIterator(); i.hasNext();) {
            Card card = i.next();
            card.setSelected(false);
        }

        return state;
    }

    public static AppState resetImageForIncorrectCards(AppState state) {
        for(ListIterator<Card> i = state.getCards().listIterator(); i.hasNext();) {
            Card card = i.next();
            if (card.hasFoundMatch() == false) {
                card.setImageId(R.mipmap.card);
            }
        }
        return state;
    }

    public static AppState resetCombo(AppState state) {
        state.setCombo(0);
        return state;
    }

    public static AppState setCombo(AppState state) {
        state.setCombo(state.getCombo() + 1);
        return state;
    }

    public static AppState setGameOver(AppState state) {
        state.setGameOver(true);
        return state;
    }

    public static AppState resetGameOver(AppState state) {
        state.setGameOver(false);
        return state;
    }

}
