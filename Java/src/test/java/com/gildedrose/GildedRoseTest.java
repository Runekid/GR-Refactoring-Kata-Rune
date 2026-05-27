package com.gildedrose;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    // --- Normal items ---

    @Test
    void normalItem_decreasesQualityAndSellInByOne() {
        Item item = new Item("+5 Dexterity Vest", 10, 20);
        new GildedRose(new Item[]{item}).updateQuality();
        assertEquals(9, item.sellIn);
        assertEquals(19, item.quality);
    }

    @Test
    void normalItem_degradesTwiceAsFastAfterSellDate() {
        Item item = new Item("+5 Dexterity Vest", 0, 10);
        new GildedRose(new Item[]{item}).updateQuality();
        assertEquals(8, item.quality);
    }

    @Test
    void normalItem_qualityNeverGoesNegative() {
        Item item = new Item("+5 Dexterity Vest", 5, 0);
        new GildedRose(new Item[]{item}).updateQuality();
        assertEquals(0, item.quality);
    }

    // --- Aged Brie ---

    @Test
    void agedBrie_increasesInQuality() {
        Item item = new Item("Aged Brie", 2, 0);
        new GildedRose(new Item[]{item}).updateQuality();
        assertEquals(1, item.quality);
    }

    @Test
    void agedBrie_increasesTwiceAsFastAfterSellDate() {
        Item item = new Item("Aged Brie", 0, 10);
        new GildedRose(new Item[]{item}).updateQuality();
        assertEquals(12, item.quality);
    }

    @Test
    void agedBrie_qualityNeverExceedsFifty() {
        Item item = new Item("Aged Brie", 2, 50);
        new GildedRose(new Item[]{item}).updateQuality();
        assertEquals(50, item.quality);
    }

    // --- Sulfuras ---

    @Test
    void sulfuras_neverDecreasesInQuality() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
        new GildedRose(new Item[]{item}).updateQuality();
        assertEquals(80, item.quality);
    }

    @Test
    void sulfuras_sellInNeverChanges() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
        new GildedRose(new Item[]{item}).updateQuality();
        assertEquals(0, item.sellIn);
    }

    // --- Backstage passes ---

    static Stream<Arguments> backstagePassQualityIncrease() {
        return Stream.of(
            Arguments.of(11, 20, 21), // more than 10 days: +1
            Arguments.of(10, 20, 22), // 10 days or less: +2
            Arguments.of(5,  20, 23), // 5 days or less: +3
            Arguments.of(1,  20, 23)  // 1 day left: +3
        );
    }

    @ParameterizedTest(name = "sellIn={0}, quality={1} → quality={2}")
    @MethodSource("backstagePassQualityIncrease")
    void backstagePass_increasesQualityByRuleAsConceptApproaches(int sellIn, int quality, int expectedQuality) {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality);
        new GildedRose(new Item[]{item}).updateQuality();
        assertEquals(expectedQuality, item.quality);
    }

    @Test
    void backstagePass_dropsToZeroAfterConcert() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20);
        new GildedRose(new Item[]{item}).updateQuality();
        assertEquals(0, item.quality);
    }

    @Test
    void backstagePass_qualityNeverExceedsFifty() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49);
        new GildedRose(new Item[]{item}).updateQuality();
        assertEquals(50, item.quality);
    }

    // --- Conjured ---

    @Test
    void conjuredItem_degradesTwiceAsFastAsNormal() {
        Item item = new Item("Conjured Mana Cake", 3, 6);
        new GildedRose(new Item[]{item}).updateQuality();
        assertEquals(4, item.quality);
    }

    @Test
    void conjuredItem_degradesFourTimesAsFastAfterSellDate() {
        Item item = new Item("Conjured Mana Cake", 0, 6);
        new GildedRose(new Item[]{item}).updateQuality();
        assertEquals(2, item.quality);
    }

    @Test
    void conjuredItem_qualityNeverGoesNegative() {
        Item item = new Item("Conjured Mana Cake", 3, 0);
        new GildedRose(new Item[]{item}).updateQuality();
        assertEquals(0, item.quality);
    }
}
