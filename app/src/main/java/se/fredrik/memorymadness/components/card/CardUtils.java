package se.fredrik.memorymadness.components.card;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import se.fredrik.memorymadness.R;

/**
 * Created by fredrik on 2016-11-04.
 */

public class CardUtils {
    public static boolean hasSelectedTwoOrMoreCards(List<Card> cards) {
        int count = 0;
        for(Iterator<Card> i = cards.iterator(); i.hasNext();) {
            Card card = i.next();
            if (card.isSelected()) count++;
        }

        return count >= 2;
    }

    public static List<Card> findSelectedCards(List<Card> cards) {
        List<Card> matches = new ArrayList<>();
        for(Iterator<Card> i = cards.iterator(); i.hasNext();) {
            Card card = i.next();
            if (card.isSelected()) {
                matches.add(card);
            }
        }

        return matches;
    }

    public static boolean sameCardsSelected(List<Card> cards) {
        List<Card> selectedCards = findSelectedCards(cards);

        if (selectedCards.size() < 2) return false;

        Card card1 = selectedCards.get(0);
        Card card2 = selectedCards.get(1);

        return card1.getImageId() == card2.getImageId();
    }

    public static boolean allCardsAreTurnedUp(List<Card> cards) {
        List<Card> matches = new ArrayList<>();
        for(Iterator<Card> i = cards.iterator(); i.hasNext();) {
            Card card = i.next();
            if (card.getImageId() != R.mipmap.card) {
                matches.add(card);
            }
        }

        return cards.size() == matches.size();
    }
}
