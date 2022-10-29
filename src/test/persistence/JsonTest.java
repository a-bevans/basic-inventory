package persistence;

import model.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkItem(String name, Integer stock, Integer price, Item item) {
        assertEquals(name, item.getName());
        assertEquals(stock, item.getStock());
        assertEquals(price, item.getPrice());
    }
}
