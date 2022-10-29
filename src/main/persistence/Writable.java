package persistence;

import org.json.JSONObject;

public interface Writable {
    //All persistance code was adapted from JsonSerializationDemo
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
