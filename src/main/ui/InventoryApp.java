package ui;

import model.Item;
import model.Store;

import java.util.List;
import java.util.Scanner;

public class InventoryApp {
    //This code was adapted from the TellerApp example code.
    private Store myStore;
    private Scanner input;

    public InventoryApp() {
        runInventory();
    }

    private void runInventory() {
        String command;
        boolean keepGoing = true;
        System.out.println("Welcome to your store!");

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        myStore = new Store();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddItem();
        } else if (command.equals("s")) {
            doSell();
        } else if (command.equals("m")) {
            doModify();
        } else if (command.equals("i")) {
            doItems();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void doItems() {
        List<String> allItems = myStore.allItems();
        for (String item : allItems) {
            System.out.println(item);
        }
    }

    private void doModify() {
        System.out.println("Press r to restock and p to change pricing.");
        String command = input.next();
        command = command.toLowerCase();

        if (command.equals("r")) {
            doRestock();
        } else if (command.equals("p")) {
            doPrice();
        }
    }

    private void doPrice() {
        String name = getItemName();
        System.out.println("New item price?");
        int price = input.nextInt();
        myStore.updatePrice(name,price);
    }

    private void doRestock() {
        String name = getItemName();
        System.out.println("Amount of new stock?");
        int stock = input.nextInt();
        myStore.restockItem(name,stock);
    }

    private void doSell() {
        String name = getItemName();
        System.out.println("Amount of item purchased?");
        int amount = input.nextInt();
        boolean validSale = myStore.makePurchase(name,amount);
        if (validSale) {
            System.out.println("Sale successful!");
        } else {
            System.out.println("Item is not in the store!");
        }
    }

    private void doAddItem() {
        String name = getItemName();
        System.out.println("Item price?");
        int price = input.nextInt();
        System.out.println("Item stock?");
        int stock = input.nextInt();

        boolean validAdd = myStore.addItem(name, price, stock);
        if (validAdd) {
            System.out.println("Item added successfully!");
        } else {
            System.out.println("Item is already in the store!");
        }
    }

    private String getItemName() {
        System.out.println("Item name?");
        return input.next();
    }

    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add an item");
        System.out.println("\ts -> sell an item");
        System.out.println("\tm -> modify an item");
        System.out.println("\ti -> view all items");
        System.out.println("\tq -> quit");
    }
}

