package ui;

import model.Item;
import model.Store;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import persistence.JsonReader;
import persistence.JsonWriter;
import java.io.FileNotFoundException;


public class GUI extends JFrame {
    private JList listItem;
    private JPanel topPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JTextField sellTextField;
    private JButton sellButton;
    private JButton saveNewButton;
    private JButton saveExistingButton;
    private JTextField itemName;
    private JTextField itemPrice;
    private JTextField itemStock;
    private JButton saveStoreButton;
    private JButton loadStoreButton;
    private JLabel inventoryLabel;
    private JPanel panelMain;
    private Store myStore;
    private List<Item> inventory;
    private DefaultListModel listItemModel;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/store.json";

    @SuppressWarnings("methodlength")
    public GUI() {
        super("My Store Project");
        this.setContentPane(this.panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        myStore = new Store();
        inventory = myStore.getInventory();
        listItemModel = new DefaultListModel();
        listItem.setModel(listItemModel);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        saveNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonNewClick(e);
            }
        });

        saveExistingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonExistClick(e);
            }
        });

        listItem.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                listSelectClick(e);
            }
        });
        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonSellClick();
            }
        });
        saveStoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveStoreClick();
            }
        });
        loadStoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadStoreClick();
            }
        });
    }

    //EFFECT: Save store to a file when the Save Store button is pressed
    private void saveStoreClick() {
        try {
            jsonWriter.open();
            jsonWriter.write(myStore);
            jsonWriter.close();
            System.out.println("Saved store to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //EFFECT: Loads store from a file when the Load Store button is pressed
    private void loadStoreClick() {
        try {
            myStore = jsonReader.read();
            System.out.println("Loaded store from " + JSON_STORE);
            inventory = myStore.getInventory();
            refreshItemList();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //EFFECTS: Sells selected item from store when the Sell button is pressed
    private void buttonSellClick() {
        int index = listItem.getSelectedIndex();
        if (index >= 0) {
            Item i = inventory.get(index);
            myStore.makePurchase(i.getName(), Integer.parseInt(sellTextField.getText()));
            refreshItemList();
        }
    }

    //EFFECTS: Saves entered parameters to a new item which is added to the store
    private void buttonNewClick(ActionEvent e) {
        String name = itemName.getText();
        int price = Integer.parseInt(itemPrice.getText());
        int stock = Integer.parseInt(itemStock.getText());
        myStore.addItem(name, price, stock);
        refreshItemList();
    }

    //EFFECTS: Updates an existing objects fields when save existing button is pressed
    private void buttonExistClick(ActionEvent e) {
        int index = listItem.getSelectedIndex();
        if (index >= 0) {
            Item i = inventory.get(index);
            i.setName(itemName.getText());
            i.setPrice(Integer.parseInt(itemPrice.getText()));
            i.setStock(Integer.parseInt(itemStock.getText()));
            refreshItemList();
        }
    }

    //EFFECTS: Loads item fields into the text boxes when item is selected from the list
    private void listSelectClick(ListSelectionEvent e) {
        int index = listItem.getSelectedIndex();
        if (index >= 0) {
            Item i = inventory.get(index);
            itemName.setText(i.getName());
            itemPrice.setText(Integer.toString(i.getPrice()));
            itemStock.setText(Integer.toString(i.getStock()));
            saveNewButton.setEnabled(true);
        } else {
            saveNewButton.setEnabled(false);
        }
    }

    //EFFECTS: Updates item list to make sure all items are shown
    public void refreshItemList() {
        listItemModel.removeAllElements();
        System.out.println("Removing all items,");
        for (Item i : inventory) {
            System.out.println("Adding item to inventory:" + i.getName());
            listItemModel.addElement(i.getName());
        }
    }

}
