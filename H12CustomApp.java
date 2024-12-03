///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           descriptive title of the program making use of this file
// Course:          course number, term, and year
//
// Author:          your name
// Email:           your @wisc.edu email address
// Lecturer's Name: name of your lecturer
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// Source or Recipient; Description
// Examples (REMOVE in your code - unless Jane Doe helped you and you helped John Doe accordingly):
// Jane Doe; helped me with for loop in reverse method
// https://docs.oracle.com/javase/tutorial/java/nutsandbolts/for.html; 
//         counting for loop
// John Doe; I helped with switch statement in main method.
// ChatGPT; Helped with debugging an off-by-one error with a loop.
//
///////////////////////////////// REFLECTION ///////////////////////////////////
//
// 1. How did you decide what parameters the read and write methods should accept?  TODO
// 2. What challenges did you encounter while reading or writing the file contents? TODO
// 3. How did you resolve the challenges?  TODO
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.io.*;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * TODO Summarize what your application does.
 *
 * @author Your Name
 */
public class H12CustomApp {

    public static ArrayList<String> makeInventoryItemArray(File file)
            throws FileNotFoundException {
        ArrayList<String> inventoryItemArray = new ArrayList<>();
        Scanner fileReader = new Scanner(file);
        while(fileReader.hasNextLine()) {
            fileReader.next();
            String item = fileReader.next();
            item = item.substring(0, item.length() - 1);

            fileReader.next();
            int quantity = fileReader.nextInt();

            if (item.contains("_")) {
                item = item.replaceAll("_", " ");
            }

            inventoryItemArray.add(item);
        }
        return inventoryItemArray;
    }

    public static ArrayList<Integer> makeInventoryQuantityArray(File file)
            throws FileNotFoundException {
        ArrayList<Integer> inventoryQuantityArray = new ArrayList<>();
        Scanner fileReader = new Scanner(file);
        while(fileReader.hasNextLine()) {
            fileReader.next();
            String item = fileReader.next();
            item = item.substring(0, item.length() - 1);

            fileReader.next();
            int quantity = fileReader.nextInt();

            if (item.contains("_")) {
                item = item.replaceAll("_", " ");
            }

            inventoryQuantityArray.add(quantity);
        }
        return inventoryQuantityArray;
    }

    /**
     * TODO: summarize what your program does and describe an example use.
     *
     * @param args TODO
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        //get information from the user via command line arguments or interaction using Scanner.
        //read part of a file - non-trivial
        //write a file - non-trivial

        File file = new File("data/inventory.txt");

        int userChoice;
        boolean validInput = false;

        ArrayList<String> inventoryItemArray = null;
        ArrayList<Integer> inventoryQuantityArray = null;

        try {
            inventoryItemArray = makeInventoryItemArray(file);
        } catch (FileNotFoundException exception) {
            System.out.println("Error: fileReader has failed in method makeInventoryItemArray()");
            isRunning = false;
        }
        try {
            inventoryQuantityArray = makeInventoryQuantityArray(file);
        } catch (FileNotFoundException exception) {
            System.out.println("Error: fileReader has failed in method " +
                               "makeInventoryQuantityArray()");
            isRunning = false;
            System.out.println("Ending...");
        }

        while(isRunning) {
            do {
                System.out.print("Choose an action:\n" +
                        "1. View inventory\n" +
                        "2. Add item to inventory\n" +
                        "3. Remove item from inventory\n" +
                        "4. End\n");
                if (scanner.hasNext()) {
                    if (scanner.hasNextInt()) {
                        userChoice = scanner.nextInt();
                        if ((userChoice == 1) || (userChoice == 2) ||
                            (userChoice == 3) || (userChoice == 4)) {
                            validInput = true;
                            switch (userChoice) {
                                case 1:
                                    viewInventory(inventoryItemArray, inventoryQuantityArray);
                                    break;
                                case 2:
                                    scanner.nextLine();

                                    boolean validInput2 = false;
                                    String itemToAdd = null;
                                    int itemToAddQuantity = -1;
                                    do {
                                        System.out.println("What item do you want to add?");
                                        if (scanner.hasNextLine()) {
                                            validInput2 = true;
                                            itemToAdd = scanner.nextLine().trim().toLowerCase();

                                            if (itemToAdd.contains(" ")) {
                                                itemToAdd = itemToAdd.replaceAll(" ",
                                                                             "_");
                                            }
                                        } else {
                                            System.out.println("Empty input!");
                                        }
                                    } while (!validInput2);

                                    boolean validInput3 = false;
                                    do {
                                        System.out.println("How many of this item " +
                                                           "do you want to add?");
                                        if (scanner.hasNext()) {
                                            if (scanner.hasNextInt()) {
                                                itemToAddQuantity = scanner.nextInt();
                                                if (itemToAddQuantity > 0) {
                                                    validInput3 = true;
                                                } else {
                                                    System.out.println("Enter an integer " +
                                                                       "greater than 0.");
                                                }
                                            } else {
                                                System.out.println("Invalid input!");
                                                scanner.next();
                                            }
                                        } else {
                                            System.out.println("Empty input!");
                                        }
                                    } while (!validInput3);

                                    addItem(file, inventoryItemArray, inventoryQuantityArray,
                                            itemToAdd, itemToAddQuantity);
                                    break;
                                case 3:
                                    scanner.nextLine();

                                    boolean validInput4 = false;
                                    String itemToRemove = null;
                                    int itemToRemoveQuantity = -1;
                                    do {
                                        System.out.println("What item do you want to remove?");
                                        if (scanner.hasNextLine()) {
                                            validInput4 = true;
                                            itemToRemove = scanner.nextLine().trim().toLowerCase();

                                            if (itemToRemove.contains(" ")) {
                                                itemToRemove = itemToRemove.replaceAll(" ",
                                                                                   "_");
                                            }
                                        } else {
                                            System.out.println("Empty input!");
                                        }
                                    } while (!validInput4);

                                    boolean validInput5 = false;
                                    do {
                                        System.out.println("How many of this item " +
                                                           "do you want to remove?");
                                        if (scanner.hasNext()) {
                                            if (scanner.hasNextInt()) {
                                                itemToRemoveQuantity = scanner.nextInt();
                                                if (itemToRemoveQuantity > 0) {
                                                    validInput5 = true;
                                                } else {
                                                    System.out.println("Enter an integer " +
                                                                       "greater than 0.");
                                                }
                                            } else {
                                                System.out.println("Invalid input!");
                                                scanner.next();
                                            }
                                        } else {
                                            System.out.println("Empty input!");
                                        }
                                    } while (!validInput5);

                                    removeItem(file, inventoryItemArray, inventoryQuantityArray,
                                               itemToRemove, itemToRemoveQuantity);
                                    break;

                                case 4:
                                    System.out.println("Ending...");
                                    isRunning = false;
                                    break;
                            }
                        } else {
                            System.out.println("Invalid input!");
                            scanner.next();
                        }
                    } else {
                        System.out.println("Invalid input!");
                        scanner.next();
                    }
                } else {
                    System.out.println("Empty input! Ending program...");
                    isRunning = false;
                    break;
                }
            } while (!validInput);
        }
        scanner.close();
    }

    public static void viewInventory(ArrayList<String> inventoryItemArray,
                                     ArrayList<Integer> inventoryQuantityArray) {
        if (!inventoryItemArray.isEmpty()) {
            System.out.println("Inventory:");
            int quantity;
            for (String item : inventoryItemArray) {
                quantity = inventoryQuantityArray.get(inventoryItemArray.indexOf(item));

                String printedItem;
                printedItem = item;

                if (item.contains("_")) {
                    printedItem = item.replaceAll("_", " ");
                }

                System.out.println(quantity + "x " + printedItem);
            }
        } else {
            System.out.println("Inventory is empty!");
        }
    }

    public static void addItem(File file, ArrayList<String> inventoryItemArray,
                               ArrayList<Integer> inventoryQuantityArray, String itemToAdd,
                               int itemToAddQuantity) {
        int quantity;
        boolean newItem = true;
        for (String item : inventoryItemArray) {
            quantity = inventoryQuantityArray.get(inventoryItemArray.indexOf(item));

            if (item.equals(itemToAdd)) {
                newItem = false;
                quantity += itemToAddQuantity;
                inventoryQuantityArray.set(inventoryItemArray.indexOf(item), quantity);
            }
        }
        if (newItem) {
            inventoryItemArray.add(itemToAdd);
            inventoryQuantityArray.add(itemToAddQuantity);
        }
        updateInventory(file, inventoryItemArray, inventoryQuantityArray);
    }

    public static void removeItem(File file, ArrayList<String> inventoryItemArray,
                                  ArrayList<Integer> inventoryQuantityArray, String itemToRemove,
                                  int itemToRemoveQuantity) {
        int quantity;
        boolean itemInInventory = false;

        for (String item : inventoryItemArray) {
            quantity = inventoryQuantityArray.get(inventoryItemArray.indexOf(item));

            if (item.equals(itemToRemove)) {
                itemInInventory = true;
                if (quantity >= itemToRemoveQuantity) {
                    quantity -= itemToRemoveQuantity;
                } else {
                    System.out.println("Inventory does not contain that much of that item!");
                }
            }
            inventoryQuantityArray.set(inventoryItemArray.indexOf(item), quantity);
            updateInventory(file, inventoryItemArray, inventoryQuantityArray);
        }
        if (!itemInInventory) {
            System.out.println("None of that item in your inventory!");
        }

        for (int i = 0; i < inventoryItemArray.size(); i++) {
            quantity = inventoryQuantityArray.get(i);

            if (quantity == 0) {
                inventoryItemArray.remove(i);
                inventoryQuantityArray.remove(i);
                i--;
                updateInventory(file, inventoryItemArray, inventoryQuantityArray);
            }
        }
    }

    public static void updateInventory(File file, ArrayList<String> inventoryItemArray,
                                       ArrayList<Integer> inventoryQuantityArray) {
        try (PrintWriter printWriter = new PrintWriter(file)) {
            int quantity;
            for (String item : inventoryItemArray) {
                quantity = inventoryQuantityArray.get(inventoryItemArray.indexOf(item));

                if (inventoryItemArray.indexOf(item) == inventoryItemArray.size() - 1) {
                    printWriter.print("item: " + item + ", quantity: " + quantity);
                } else {
                    printWriter.println("item: " + item + ", quantity: " + quantity);
                }
            }
        } catch (IOException exception) {
            System.out.println("Error: printWriter has failed.");
        }
    }
}
