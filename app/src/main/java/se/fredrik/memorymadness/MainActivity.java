package se.fredrik.memorymadness;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.UUID;

import se.fredrik.memorymadness.common.Action;
import se.fredrik.memorymadness.common.Actions;
import se.fredrik.memorymadness.common.AppState;
import se.fredrik.memorymadness.common.Store;
import se.fredrik.memorymadness.common.StoreSubscriber;
import se.fredrik.memorymadness.components.card.CardAdapter;
import se.fredrik.memorymadness.components.card.CardClickListener;
import se.fredrik.memorymadness.components.card.CardUtils;
import se.fredrik.memorymadness.components.card.IncorrectCardTurner;
import se.fredrik.memorymadness.components.overlay.OverlayClickListener;
import se.fredrik.memorymadness.components.progressBar.ProgressBarAnimation;
import se.fredrik.memorymadness.components.progressBar.ProgressBarTimer;

public class MainActivity extends AppCompatActivity implements StoreSubscriber {

    private static final String WINNER_TEXT = "You won, such wow! :)";
    private static final long TIME_PER_PAIR = 3000;
    private static final String LOSER_TEXT = "You lost, much sadness! :(";
    private static final float ANIMATION_MAX_VALUE = TIME_PER_PAIR / 29;
    private final int CARD_TURN_DELAY = 1000;
    private UUID token;
    private GridView gridview;
    private Handler handler;
    private TextView score;
    private TextView combo;
    private FrameLayout overlay;
    private TextView overlayText;
    private ProgressBar progressBar;
    private ProgressBarAnimation animation;
    private ProgressBarTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridview = (GridView) findViewById(R.id.card_container);
        gridview.setAdapter(new CardAdapter(this, Store.getState().getCards()));
        gridview.setOnItemClickListener(new CardClickListener());

        score = (TextView) findViewById(R.id.score);
        combo = (TextView) findViewById(R.id.combo);
        overlayText = (TextView) findViewById(R.id.overlayText);

        overlay = (FrameLayout) findViewById(R.id.overlay);
        overlay.setOnClickListener(new OverlayClickListener());

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.progress_bar));

        handler = new Handler();

        animation = new ProgressBarAnimation(progressBar, 0, ANIMATION_MAX_VALUE);
        animation.setDuration(TIME_PER_PAIR);

        timer = new ProgressBarTimer(animation, progressBar);

        this.token = Store.subscribe(this);
        Store.dispatch(new Action(Actions.RESET_GAME));
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Store.unSubscribe(this.token);
    }

    @Override
    public void onStateUpdate(AppState state) {
        gridview.invalidateViews();

        score.setText(state.getScoreText());
        combo.setText(state.getComboText());

        boolean hasSelectedTwoOrMoreCards = CardUtils.hasSelectedTwoOrMoreCards(state.getCards());
        boolean areSameImages = CardUtils.sameCardsSelected(state.getCards());
        boolean gameIsWon = CardUtils.allCardsAreTurnedUp(state.getCards());

        if (gameIsWon) {
            overlayText.setText(WINNER_TEXT);
            overlay.setVisibility(View.VISIBLE);
            return;
        }
        else if(state.isGameOver()) {
            overlayText.setText(LOSER_TEXT);
            overlay.setVisibility(View.VISIBLE);
            return;
        }
        else if(hasSelectedTwoOrMoreCards && areSameImages) {
            Store.dispatch(new Action(Actions.SET_CARDS_TO_UNSELECTED));
            Store.dispatch(new Action(Actions.SET_COMBO));
            Store.dispatch(new Action(Actions.SET_SCORE));
            progressBar.clearAnimation();
            progressBar.startAnimation(animation);
            handler.removeCallbacks(timer);
            handler.postDelayed(timer, TIME_PER_PAIR);
            return;
        }
        else if(hasSelectedTwoOrMoreCards) { // not the same images
            // if cards are not matches delay turning them back
            handler.postDelayed(new IncorrectCardTurner(), CARD_TURN_DELAY);
            progressBar.clearAnimation();
            progressBar.startAnimation(animation);
            handler.removeCallbacks(timer);
            handler.postDelayed(timer, TIME_PER_PAIR);
            return;
        }
    }
}