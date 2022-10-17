package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StoreTest {

    Store store;

    @BeforeEach
    void setup() {
        store = new Store();
    }

    @Test
    void addItemNotInStoreTest() {
        boolean validAdd = store.addItem("Drink", 1, 20);

        assertEquals(1, store.getInventory().size());
        assertEquals("Drink", store.getInventory().get(0).getName());
        assertTrue(validAdd);
    }

    @Test
    void addItemInStoreTest() {
        boolean validAdd1 = store.addItem("Drink", 1, 20);
        boolean validAdd2 = store.addItem("Drink", 2, 6);
        Item first = store.getInventory().get(0);

        assertEquals(1, store.getInventory().size());
        assertEquals("Drink", first.getName());
        assertEquals(1, first.getPrice());
        assertEquals(20, first.getStock());
        assertTrue(validAdd1);
        assertFalse(validAdd2);
    }

    @Test
    void addTwoDifferentItemsNotInStoreTest() {
        store.addItem("Drink", 1, 20);
        store.addItem("Food", 2, 6);
        Item first = store.getInventory().get(0);
        Item second = store.getInventory().get(1);

        assertEquals(2, store.getInventory().size());
        assertEquals("Drink", first.getName());
        assertEquals(1, first.getPrice());
        assertEquals(20, first.getStock());
        assertEquals("Food", second.getName());
        assertEquals(2, second.getPrice());
        assertEquals(6, second.getStock());
    }

    @Test
    void updatePriceNotInStoreItemTest() {
        store.addItem("Drink", 1, 20);
        store.updatePrice("Food", 10);
        Item first = store.getInventory().get(0);

        assertEquals(1, store.getInventory().size());
        assertEquals("Drink", first.getName());
        assertEquals(1, first.getPrice());
    }

    @Test
    void updatePriceInStoreItemTest() {
        store.addItem("Drink", 1, 20);
        store.updatePrice("Drink", 10);
        Item first = store.getInventory().get(0);

        assertEquals(1, store.getInventory().size());
        assertEquals("Drink", first.getName());
        assertEquals(10, first.getPrice());
    }

    @Test
    void restockItemNotInStoreTest() {
        store.addItem("Drink", 1, 20);
        store.restockItem("Food", 10);
        Item first = store.getInventory().get(0);

        assertEquals(1, store.getInventory().size());
        assertEquals("Drink", first.getName());
        assertEquals(20, first.getStock());
    }

    @Test
    void restockItemInStoreTest() {
        store.addItem("Drink", 1, 20);
        store.restockItem("Drink", 10);
        Item first = store.getInventory().get(0);

        assertEquals(1, store.getInventory().size());
        assertEquals("Drink", first.getName());
        assertEquals(30, first.getStock());
    }

    @Test
    void makePurchaseItemNotInStoreTest() {
        store.addItem("Drink", 5, 20);
        boolean success = store.makePurchase("Food", 2);

        assertFalse(success);
        assertEquals(0, store.getCurrentRevenue());
    }

    @Test
    void makePurchaseNotEnoughStockTest() {
        store.addItem("Drink", 5, 20);
        boolean success = store.makePurchase("Drink", 21);

        assertFalse(success);
        assertEquals(0, store.getCurrentRevenue());
    }

    @Test
    void makePurchaseJustEnoughStockTest() {
        store.addItem("Drink", 5, 20);
        boolean success = store.makePurchase( "Drink", 20);

        assertTrue(success);
        assertEquals(100, store.getCurrentRevenue());
    }

    @Test
    void makePurchaseEnoughStockTest() {
        store.addItem("Drink", 5, 20);
        boolean success = store.makePurchase( "Drink", 5);

        assertTrue(success);
        assertEquals(25, store.getCurrentRevenue());
    }
}