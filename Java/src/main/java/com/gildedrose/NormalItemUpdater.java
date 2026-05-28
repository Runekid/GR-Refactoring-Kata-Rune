package com.gildedrose;

public class NormalItemUpdater implements ItemUpdater {
    public void update(Item item) {
        item.sellIn--;
        degradeNormal(item, 1);
    }
}
