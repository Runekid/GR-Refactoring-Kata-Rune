package com.gildedrose;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BackstagePassUpdaterTest {
    private final ItemUpdater updater = new BackstagePassUpdater();

    static Stream<Arguments> qualityIncreaseByDaysRemaining() {
        return Stream.of(
            Arguments.of(11, 20, 21), // more than 10 days: +1
            Arguments.of(10, 20, 22), // 10 days or less: +2
            Arguments.of(5,  20, 23), // 5 days or less: +3
            Arguments.of(1,  20, 23)  // 1 day left: +3
        );
    }

    @ParameterizedTest(name = "sellIn={0}, quality={1} → quality={2}")
    @MethodSource("qualityIncreaseByDaysRemaining")
    void increasesInQualityByRuleAsDateApproaches(int sellIn, int quality, int expectedQuality) {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality);
        updater.update(item);
        assertEquals(expectedQuality, item.quality);
    }

    @Test
    void dropsToZeroAfterConcert() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20);
        updater.update(item);
        assertEquals(0, item.quality);
    }

    @Test
    void qualityNeverExceedsFifty() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49);
        updater.update(item);
        assertEquals(50, item.quality);
    }
}
