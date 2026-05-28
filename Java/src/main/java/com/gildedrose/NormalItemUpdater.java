package com.gildedrose;

public class NormalItemUpdater implements ItemUpdater {
    public void update(Item item) {
        item.sellIn--;
        decreaseQuality(item, 1);
    }
}
