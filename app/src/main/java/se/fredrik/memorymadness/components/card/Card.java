package se.fredrik.memorymadness.components.card;

import java.util.UUID;

/**
 * Created by fredrik on 2016-11-03.
 */

public class Card {
    private final UUID id;
    private int imageId;
    private boolean showImage;
    private boolean hasFoundMatch;
    private boolean isSelected;

    public Card(int imageId) {
        this(imageId, false);
    }

    public Card(int imageId, boolean isSelected) {
        this.id = UUID.randomUUID();
        this.imageId = imageId;
        this.isSelected = isSelected;
        this.hasFoundMatch = false;
        this.showImage = false;
    }

    public UUID getId() {
        return id;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getImageId() {
        return imageId;
    }

    public boolean hasFoundMatch() {
        return hasFoundMatch;
    }

    public void setHasFoundMatch(boolean hasFoundMatch) {
        this.hasFoundMatch = hasFoundMatch;
    }

    public boolean showImage() {
        return showImage;
    }

    public void setShowImage(boolean showImage) {
        this.showImage = showImage;
    }
}
