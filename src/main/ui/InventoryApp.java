package ui;

import model.Item;
import model.Store;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class InventoryApp {
    //This code was adapted from the TellerApp example code.
    private Store myStore;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/store.json";

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
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddItem();
        } else if (command.equals("p")) {
            doSell();
        } else if (command.equals("m")) {
            doModify();
        } else if (command.equals("i")) {
            doItems();
        } else if (command.equals("r")) {
            doRevenue();
        } else if (command.equals("s")) {
            doSave();
        } else if (command.equals("l")) {
            doLoad();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void doItems() {
        System.out.println(myStore.allItems());
    }

    private void doRevenue() {
        System.out.println(myStore.getCurrentRevenue());
    }

    private void doSave() {
        try {
            jsonWriter.open();
            jsonWriter.write(myStore);
            jsonWriter.close();
            System.out.println("Saved store to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void doLoad() {
        try {
            myStore = jsonReader.read();
            System.out.println("Loaded store from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
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
        System.out.println("\tp -> sell an item");
        System.out.println("\tr -> check revenue");
        System.out.println("\tm -> modify an item");
        System.out.println("\ti -> view all items");
        System.out.println("\ts -> save your store");
        System.out.println("\tl -> load your store");
        System.out.println("\tq -> quit");
    }
}

