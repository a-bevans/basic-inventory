package persistence;

import model.Item;
import model.Store;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Store store = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyStore() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyStore.json");
        try {
            Store store = reader.read();
            assertEquals(0, store.getInventory().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralStore() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralStore.json");
        try {
            Store store = reader.read();
            List<Item> items = store.getInventory();
            assertEquals(2, items.size());
            checkItem("Food",40,5, items.get(0));
            checkItem("Beer",18,4, items.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}