package persistence;

import model.Item;
import model.Store;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads Store from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Store from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Store read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseStore(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Store from JSON object and returns it
    private Store parseStore(JSONObject jsonObject) {
        Integer revenue = jsonObject.getInt("currentRevenue");
        Store store = new Store();
        store.setCurrentRevenue(revenue);
        addItems(store, jsonObject);
        return store;
    }

    // MODIFIES: store    // EFFECTS: parses items from JSON object and adds them to Store
    private void addItems(Store store, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("items");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addItem(store, nextItem);
        }
    }

    // MODIFIES: store    // EFFECTS: parses Item from JSON object and adds it to Store
    private void addItem(Store store, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int price = jsonObject.getInt("price");
        int stock = jsonObject.getInt("stock");
        store.addItem(name, price, stock);
    }
}
