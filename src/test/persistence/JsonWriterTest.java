package persistence;

import model.Item;
import model.Store;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Store store = new Store();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyStore() {
        try {
            Store store = new Store();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyStore.json");
            writer.open();
            writer.write(store);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyStore.json");
            store = reader.read();
            assertEquals(0, store.getInventory().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralStore() {
        try {
            Store store = new Store();
            store.addItem("Food", 5, 40);
            store.addItem("Drink", 4, 26);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralStore.json");
            writer.open();
            writer.write(store);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralStore.json");
            store = reader.read();
            List<Item> items = store.getInventory();
            assertEquals(2, items.size());
            checkItem("Food",40,5, items.get(0));
            checkItem("Drink",26,4, items.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}