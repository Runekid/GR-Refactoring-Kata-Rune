package com.gildedrose;

public class ConjuredItemUpdater implements ItemUpdater {
    public void update(Item item) {
        item.sellIn--;
        degradeNormal(item, 2);
    }
}
