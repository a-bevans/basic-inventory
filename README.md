# Store Inventory Manager

## Project Description

In order to run a successful store, it is key to keep track of the current stock and profits. This allows a business
owner to make important decisions on the future of their venture. A simple program to keep tack of inventory and profits
can help keep this data organized.

This program has applications for any small business owner in order to keep organized with their stock and to not
oversell. 

The features in this program will include:
- Adding new items with a name and price
- Replenish items stock
- Sell items 
- Check revenue
- Check stock of an item

This project is of interest to me since I am involved in small businesses that currently use messy Excel sheets and feel
limited with what they can do with them. Making a program to help simplify these basic tasks would remove the learning
curve of Excel and allow business owners to focus purely on their business.

## User Stories

- As a user, I want to be able to add a new item to the store.
- As a user, I want to select an item and view and modify the stock and sale price.
- As a user, I want to be able to select my store and view all available items.
- As a user, I want to be able to select an item and sell an amount of the item from the store.
- As a user, I want to be able to save my store to a file.
- As a user, I want to be able to load my store from a file.

## Instructions for User

- You can add new items to a store by filling in the fields and clicking "Save New".
- You can edit items in a store by selecting the item, filling in the fields, and clicking "Save Existing".
- You can locate my visual component by opening the application and finding the shopping cart icon.
- You can save the state of the store by clicking "Save Store".
- You can reload the state of the store by clicking "Load Store".

## Phase 4: Task 2

Fri Dec 02 14:30:47 PST 2022
Added Food to the store.


Fri Dec 02 14:30:47 PST 2022
Added Drink to the store.


Fri Dec 02 14:30:47 PST 2022
Added Beef to the store.


Fri Dec 02 14:30:47 PST 2022
Added Pork to the store.


Fri Dec 02 14:30:57 PST 2022
Pork name changed to Porky


Fri Dec 02 14:30:57 PST 2022
Porky stock changed to 32


Fri Dec 02 14:31:43 PST 2022
Added Bacon to the store.

## Phase 4: Task 3

Looking at my UML diagram, a glaring opportunity to refactor my code is in the GUI class. The GUI class has a contains
list association relationship with the Item class. However, GUI also has a contains association relationship with store,
which also has a contains list association relationship with the Item class. Due to this redundancy, the GUI should be
refactored to use the stores relationship with the list of items to reduce coupling. This could be done by better
utilizing the getInventory method in store in the GUI class so the request inventory field is not necessary.
