package se.fredrik.memorymadness.components.card;

import java.util.UUID;

/**
 * Created by fredrik on 2016-11-03.
 */

public class Card {
    private final UUID id;
    private int imageId;
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

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public boolean hasFoundMatch() {
        return hasFoundMatch;
    }

    public void setHasFoundMatch(boolean hasFoundMatch) {
        this.hasFoundMatch = hasFoundMatch;
    }
}
