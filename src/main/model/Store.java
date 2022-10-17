package model;

import java.util.ArrayList;
import java.util.List;

//Represents a store that has a list of items, name, and a profit
public class Store {

    private final String name;
    private int currentRevenue;
    private List<Item> inventory;


    /*
     * REQUIRES: storeName has a non-zero length
     * EFFECTS: Creates a store with a name storeName; has no items
     *          and zero profit
     */
    public Store(String storeName) {
        this.name = storeName;
        currentRevenue = 0;
        inventory = new ArrayList<>();
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds item to store if there is not already an item with that name
     */
    public void addItem(String name, int price, int stock) {
        if (findItem(name) == null) {
            Item newItem = new Item(name, price, stock);
            inventory.add(newItem);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: updates items price if an item with that name exists in the store
     */
    public void updatePrice(String name, int price) {
        Item item = findItem(name);
        if (!(findItem(name) == null)) {
            item.updatePrice(price);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: updates items stock if an item with that name exists in the store
     */
    public void restockItem(String name, int amount) {
        Item item = findItem(name);
        if (!(item == null)) {
            item.addStock(amount);
        }
    }

    /*
     * EFFECTS: returns a list of all items at the store with their name and item information
     */
    public List<String> allItems() {
        List<String> itemInfo = new ArrayList<>();
        String info;
        String price;
        String stock;
        for (Item item : inventory) {
            price = Integer.toString(item.getPrice());
            stock = Integer.toString(item.getStock());
            info = item.getName() + ", $" + price + ", " + stock + "left";
            itemInfo.add(info);
        }
        return itemInfo;
    }

    /*
     * REQUIRES: amount > 0, name has non zero length
     * EFFECTS: sells an amount of given item and increases revenue to match the sale.
     *          returns true if purchase is successful and false otherwise
     */
    public boolean makePurchase(int amount, String name) {
        boolean success = false;
        Item item = findItem(name);

        if (!(item == null)) {
            if (item.getStock() >= amount) {
                this.currentRevenue += item.sellItem(amount);
                success = true;
            }
        }

        return success;
    }

    /*
     * REQUIRES: name has a non-zero length
     * EFFECTS: Returns the item with given name.
     *          If the item doesn't exist in the store, return null
     */
    private Item findItem(String name) {
        Item item = null;
        for (Item checkItem : inventory) {
            //check if item exists
            if (name.equals(checkItem.getName())) {
                item = checkItem;
                break;
            }
        }
        return item;
    }

    public String getName() {
        return name;
    }

    public int getCurrentRevenue() {
        return currentRevenue;
    }
}

