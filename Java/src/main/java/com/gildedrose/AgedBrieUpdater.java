package com.gildedrose;

public class AgedBrieUpdater implements ItemUpdater {

    public void update(Item item) {
        item.sellIn--;
        increaseQuality(item);
        if (item.sellIn < 0) {
            increaseQuality(item);
        }
    }
}
