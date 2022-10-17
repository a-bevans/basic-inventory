package model;

//Represents an item in the store with a name, cost, sales price, stock
public class Item {
    private final String name;
    private int price;
    private int stock;

    public Item(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    //MODIFIES: this
    //EFFECT: adds amount of item to the stock
    public void addStock(int amount) {
        this.stock += amount;
    }

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
