package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {
    @Test
    void routesAgedBrieToCorrectUpdater() {
        Item item = new Item("Aged Brie", 2, 0);
        new GildedRose(new Item[]{item}).updateQuality();
        assertEquals(1, item.quality);
    }

    @Test
    void routesNormalItemToNormalUpdater() {
        Item item = new Item("+5 Dexterity Vest", 10, 20);
        new GildedRose(new Item[]{item}).updateQuality();
        assertEquals(19, item.quality);
    }
}
