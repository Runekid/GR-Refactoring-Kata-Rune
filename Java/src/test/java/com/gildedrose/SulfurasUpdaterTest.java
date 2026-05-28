package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SulfurasUpdaterTest {
    private final ItemUpdater updater = new SulfurasUpdater();

    @Test
    void qualityNeverChanges() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
        updater.update(item);
        assertEquals(80, item.quality);
    }

    @Test
    void sellInNeverChanges() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
        updater.update(item);
        assertEquals(0, item.sellIn);
    }
}
