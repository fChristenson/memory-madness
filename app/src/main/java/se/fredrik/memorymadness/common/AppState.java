package se.fredrik.memorymadness.common;

import java.util.ArrayList;
import java.util.List;

import se.fredrik.memorymadness.R;
import se.fredrik.memorymadness.components.card.Card;

/**
 * Created by fredrik on 2016-11-03.
 */

public class AppState {
    private final List<Card> cards;
    private int score;
    private int combo;
    private boolean isGameOver;

    public AppState() {
        this.isGameOver = false;
        this.score = 0;
        this.combo = 0;
        this.cards = new ArrayList<>();
        for(int i = 0; i < 24; i++) {
            this.cards.add(new Card(R.mipmap.card));
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public String getScoreText() {
        return Utils.makeScoreText(this.score);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getComboText() {
        return Utils.makeComboText(this.combo);
    }

    public int getCombo() {
        return combo;
    }

    public void setCombo(int combo) {
        this.combo = combo;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

}
