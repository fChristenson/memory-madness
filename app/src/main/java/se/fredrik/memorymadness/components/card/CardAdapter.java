package se.fredrik.memorymadness.components.card;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

import se.fredrik.memorymadness.R;

/**
 * Created by fredrik on 2016-11-02.
 */

public class CardAdapter extends BaseAdapter {

    private static final int IMAGE_WIDTH = 300;
    private static final int IMAGE_HEIGHT = 300;
    private Context mContext;
    private List<Card> cards;

    public CardAdapter(Context c, List<Card> cards) {
        this.mContext = c;
        this.cards = cards;
    }

    @Override
    public int getCount() {
        return this.cards.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        if (null == view) {
            return makeNewImageView(this.mContext, this.cards.get(i));
        }
        else {
            imageView = (ImageView) view;
            return setCardImage(imageView, this.cards.get(i));
        }
    }

    private static ImageView makeNewImageView(Context mContext, Card card) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return setCardImage(imageView, card);
    }

    private static ImageView setCardImage(ImageView imageView, Card card) {
        if(card.showImage()) {
            imageView.setImageResource(card.getImageId());
        }
        else {
            imageView.setImageResource(R.mipmap.card);
        }

        return imageView;
    }
}
