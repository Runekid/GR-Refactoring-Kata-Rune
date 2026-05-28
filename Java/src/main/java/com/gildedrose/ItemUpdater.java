package com.gildedrose;

public interface ItemUpdater {
    void update(Item item);

    default void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    default void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }

    default void decreaseQuality(Item item, int multiplier) {
        int times = item.sellIn < 0 ? multiplier * 2 : multiplier;
        for (int i = 0; i < times; i++) {
            decreaseQuality(item);
        }
    }
}
