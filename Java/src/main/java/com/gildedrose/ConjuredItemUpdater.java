package com.gildedrose;

public class ConjuredItemUpdater implements ItemUpdater {
    public void update(Item item) {
        item.sellIn--;
        decreaseQuality(item, 2);
    }
}
