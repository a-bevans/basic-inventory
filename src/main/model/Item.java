package model;

//Represents an item in the store with a name, cost, sales price, stock
public class Item {
    private final String name;
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
        return (this.price * amount);
    }

    public void updatePrice(int price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
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

}
