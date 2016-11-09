package se.fredrik.memorymadness.components.card;

import java.util.List;

import se.fredrik.memorymadness.common.Action;
import se.fredrik.memorymadness.common.Actions;
import se.fredrik.memorymadness.common.Store;

/**
 * Created by fredrik on 2016-11-04.
 */

public class IncorrectCardTurner implements Runnable {
    @Override
    public void run() {
        List<Card> selectedImages = CardUtils.findSelectedCards(Store.getState().getCards());

        Store.dispatch(new Action(Actions.SET_CARDS_TO_UNSELECTED));
        Store.dispatch(new Action(Actions.RESET_IMAGE_FOR_INCORRECT_CARDS));

        boolean cardsHaveBeenMatched = CardUtils.cardsHaveAllBeenMatched(selectedImages);

        if(!cardsHaveBeenMatched) {
            Store.dispatch(new Action(Actions.RESET_COMBO_STREAK));
        }
    }
}
