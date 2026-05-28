package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConjuredItemUpdaterTest {
    private final ItemUpdater updater = new ConjuredItemUpdater();

    @Test
    void decreasesInQualityTwiceAsFastAsNormal() {
        Item item = new Item("Conjured Mana Cake", 3, 6);
        updater.update(item);
        assertEquals(4, item.quality);
    }

    @Test
    void decreasesInQualityFourTimesAsFastAfterSellDate() {
        Item item = new Item("Conjured Mana Cake", 0, 6);
        updater.update(item);
        assertEquals(2, item.quality);
    }

    @Test
    void qualityNeverGoesNegative() {
        Item item = new Item("Conjured Mana Cake", 3, 0);
        updater.update(item);
        assertEquals(0, item.quality);
    }
}
