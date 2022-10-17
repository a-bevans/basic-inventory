package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    Item item;
    @BeforeEach
    void setup() {
        item = new Item("Drink", 1, 20);
    }

    @Test
    void addStockTest() {
        item.addStock(2);
        assertEquals(22, item.getStock());
    }

    @Test
    void sellItemTest() {
        int revenue = item.sellItem(2);
        assertEquals(18, item.getStock());
        assertEquals(2, revenue);
    }
}