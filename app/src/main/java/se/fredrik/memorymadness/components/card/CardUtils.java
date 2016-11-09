package se.fredrik.memorymadness.components.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import se.fredrik.memorymadness.R;

/**
 * Created by fredrik on 2016-11-04.
 */

public class CardUtils {
    public static List<Card> makeCards() {
        int[] cardIdArray = {
          R.mipmap.card_1, R.mipmap.card_2, R.mipmap.card_3, R.mipmap.card_4,
          R.mipmap.card_5, R.mipmap.card_6, R.mipmap.card_7, R.mipmap.card_8,
          R.mipmap.card_9, R.mipmap.card_10, R.mipmap.card_11, R.mipmap.card_12
        };

        List<Card> cards = new ArrayList<>();
        for(int id : cardIdArray) {
            cards.add(new Card(id));
            cards.add(new Card(id));
        }

        return cards;
    }

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
            if (card.showImage()) {
                matches.add(card);
            }
        }

        return cards.size() == matches.size();
    }

    public static boolean cardsHaveAllBeenMatched(List<Card> cards) {
        boolean result = true;

        for(Card card : cards) {
            if(!card.hasFoundMatch()) {
                result = false;
                break;
            }
        }

        return result;
    }

    public static List<Card> shuffleCards(List<Card> cards) {
        long seed = System.nanoTime();
        Collections.shuffle(cards, new Random(seed));
        Collections.shuffle(cards, new Random(seed));
        return cards;
    }
}
