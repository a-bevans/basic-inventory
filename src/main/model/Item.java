package model;


import org.json.JSONObject;
import persistence.Writable;

//Represents an item in the store with a name, cost, sales price, stock
public class Item implements Writable {
    private String name;
    private int price;
    private int stock;

    //REQUIRES: name is non zero length, price > 0
    //EFFECT: creates item with a name, price in dollars, and an initial stock
    public Item(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    //REQUIRES: amount > 0
    //MODIFIES: this
    //EFFECT: adds amount of item to the stock
    public void addStock(int amount) {
        this.stock += amount;
    }

    //REQUIRES: amount > 0, amount <= stock
    //MODIFIES: this
    //EFFECT: removes amount of item from the stock and returns profit from the sale
    public int sellItem(int amount) {
        this.stock -= amount;
        EventLog.getInstance().logEvent(new Event(amount + " of " + name + " sold."));
        return (this.price * amount);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("price", price);
        json.put("stock", stock);
        return json;
    }

    public void updatePrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setName(String name) {
        EventLog.getInstance().logEvent(new Event(this.name + " name changed to " + name));
        this.name = name;
    }

    public void setPrice(int price) {
        EventLog.getInstance().logEvent(new Event(this.name + " price changed to " + price));
        this.price = price;
    }

    public void setStock(int stock) {
        EventLog.getInstance().logEvent(new Event(this.name + " stock changed to " + stock));
        this.stock = stock;
    }

}
