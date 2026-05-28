package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateItem(item);
        }
    }

    private void updateItem(Item item) {
        boolean isAgedBrie = item.name.equals("Aged Brie");
        boolean isBackstagePass = item.name.equals("Backstage passes to a TAFKAL80ETC concert");
        boolean isSulfuras = item.name.equals("Sulfuras, Hand of Ragnaros");
        boolean isConjured = item.name.equals("Conjured Mana Cake");

        if (isSulfuras) {
            return;
        }

        item.sellIn = item.sellIn - 1;

        if (isAgedBrie) {
            increaseQuality(item);
            if (item.sellIn < 0) {
                increaseQuality(item);
            }
        } else if (isBackstagePass) {
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
        } else if (isConjured) {
            degradeNormal(item, 2);
        } else {
            degradeNormal(item, 1);
        }
    }
    private void degradeNormal(Item item, int multiplier) {
        int times = item.sellIn < 0 ? multiplier * 2 : multiplier;
        for (int i = 0; i < times; i++) {
            decreaseQuality(item);
        }
    }

    private void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    private void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }
}
