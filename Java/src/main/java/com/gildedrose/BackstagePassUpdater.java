package com.gildedrose;

public class BackstagePassUpdater implements ItemUpdater{

    public void update(Item item) {
        item.sellIn--;
        if (item.sellIn < 0) {
            item.quality = 0;
        } else {
            if (item.sellIn < 5) {
                increaseQuality(item);
            }
            if (item.sellIn < 10) {
                increaseQuality(item);
            }
            increaseQuality(item);
        }
    }
}
