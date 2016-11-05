package se.fredrik.memorymadness.components.card;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import java.util.HashMap;
import java.util.Map;

import se.fredrik.memorymadness.common.Action;
import se.fredrik.memorymadness.common.Actions;
import se.fredrik.memorymadness.common.Payloads;
import se.fredrik.memorymadness.common.Store;

/**
 * Created by fredrik on 2016-11-03.
 */

public class CardClickListener implements OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (!CardUtils.hasSelectedTwoOrMoreCards(Store.getState().getCards())) {
            Map<Payloads, Object> payload = new HashMap<>();
            payload.put(Payloads.SELECTED_CARD_ID, Store.getState().getCards().get(i).getId());

            Store.dispatch(new Action(Actions.CARD_CLICKED, payload));
        }
    }
}
