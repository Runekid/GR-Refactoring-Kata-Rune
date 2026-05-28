package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AgedBrieUpdaterTest {
    private final ItemUpdater updater = new AgedBrieUpdater();

    @Test
    void increasesInQuality() {
        Item item = new Item("Aged Brie", 2, 0);
        updater.update(item);
        assertEquals(1, item.quality);
    }

    @Test
    void increasesInQualityTwiceAsFastAfterSellDate() {
        Item item = new Item("Aged Brie", 0, 10);
        updater.update(item);
        assertEquals(12, item.quality);
    }

    @Test
    void qualityNeverExceedsFifty() {
        Item item = new Item("Aged Brie", 2, 50);
        updater.update(item);
        assertEquals(50, item.quality);
    }
}
