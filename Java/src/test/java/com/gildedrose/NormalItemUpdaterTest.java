package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NormalItemUpdaterTest {
    private final ItemUpdater updater = new NormalItemUpdater();

    @Test
    void decreasesQualityAndSellInByOne() {
        Item item = new Item("+5 Dexterity Vest", 10, 20);
        updater.update(item);
        assertEquals(9, item.sellIn);
        assertEquals(19, item.quality);
    }

    @Test
    void degradesTwiceAsFastAfterSellDate() {
        Item item = new Item("+5 Dexterity Vest", 0, 10);
        updater.update(item);
        assertEquals(8, item.quality);
    }

    @Test
    void qualityNeverGoesNegative() {
        Item item = new Item("+5 Dexterity Vest", 5, 0);
        updater.update(item);
        assertEquals(0, item.quality);
    }
}
