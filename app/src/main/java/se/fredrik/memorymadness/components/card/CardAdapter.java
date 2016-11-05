package se.fredrik.memorymadness.components.card;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by fredrik on 2016-11-02.
 */

public class CardAdapter extends BaseAdapter {

    private static final int IMAGE_WIDTH = 254;
    private static final int IMAGE_HEIGHT = 254;
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
            return makeNewImageView(this.mContext, this.cards.get(i).getImageId());
        }
        else {
            imageView = (ImageView) view;
            imageView.setImageResource(this.cards.get(i).getImageId());
            return imageView;
        }
    }

    private static ImageView makeNewImageView(Context mContext, int imageId) {
        ImageView imageView = new ImageView(mContext);
        imageView.setLayoutParams(new GridView.LayoutParams(IMAGE_WIDTH, IMAGE_HEIGHT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(imageId);

        return imageView;
    }
}
