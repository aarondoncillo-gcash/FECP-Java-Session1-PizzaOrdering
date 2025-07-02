package org.example;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void addOrder(ArrayList<String> pizzas, ArrayList<Integer> quantities, String pizzaType, int quantity) {
        // check if quantity is positive
        if (validateInput(quantity)){
            // Add new pizza orders. Add data to both arraylist
            pizzas.add(pizzaType);
            quantities.add(quantity);
        }
    }

    public static void updateOrder(ArrayList<Integer> quantities, int index, int newQuantity) {
        // check if new value of quantity is positive and index is within the valid range of arraylist
        if (validateInput(newQuantity) && validateInput(index, quantities))
            // Update an order’s quantity.
            quantities.set(index, newQuantity);
    }

    public static void removeOrder(ArrayList<String> pizzas, ArrayList<Integer> quantities, int index) {
       // check if index is within the valid range of arraylist
        if (validateInput(index, quantities)) {
            //Remove an order. Delete data from both arraylist
            pizzas.remove(index);
            quantities.remove(index);
        }

    }

    public static void printOrders(ArrayList<String> pizzas, ArrayList<Integer> quantities) {
        // List all current orders.
        System.out.println("--- Current Orders ---");
        for (int i = 0; i < pizzas.size(); ++i) {
            System.out.printf("%d. %s x %d\n", i+1, pizzas.get(i), quantities.get(i));
        }
    }

    // check if input is greater than 0
    public static boolean validateInput(int quantity) {
        return quantity > 0;
    }

    // check if array list is not empty and index is within the valid range
    public static boolean validateInput(int index, ArrayList<Integer> quantities) {
        return !quantities.isEmpty() && (index >= 0 && index < quantities.size());
    }

    // handles non-digit input to prevent program termination
    public static int getDigitInput(Scanner sc) {
        int digit = -999;
        try {
            digit = sc.nextInt();
        } catch (Exception e) {
        }
        return digit;
    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Stores pizza orders
        ArrayList<String> pizzas = new ArrayList<>();
        // Stores quantity of each pizza order
        ArrayList<Integer> quantities = new ArrayList<>();

        boolean keepOrdering = true;
        int option;

        System.out.println("1. Add Order");
        System.out.println("2. Update Order");
        System.out.println("3. Remove Order");
        System.out.println("4. View Order");
        System.out.println("5. Exit");
        while (keepOrdering) {
            System.out.print("Choose option: ");
            // get user input
            option = getDigitInput(sc);
            // prevent skipping the next input
            sc.nextLine();

            // Execute the action based on user input
            switch (option) {
                // Add order
                case 1:
                {
                    System.out.print("Pizza type: ");
                    String pizzaType = sc.nextLine();
                    System.out.print("Quantity: ");
                    int quantity = getDigitInput(sc);
                    // prevent skipping the next input
                    sc.nextLine();
                    // input validation
                    while(!validateInput(quantity)) {
                        System.out.println("Quantity must be positive" );
                        System.out.print("Quantity: ");
                        quantity = getDigitInput(sc);
                        // prevent skipping the next input
                        sc.nextLine();
                    }
                    // place new order
                    addOrder(pizzas, quantities, pizzaType, quantity);
                    break;
                }
                //Update order
                case 2:
                {
                    // Check if there are existing orders before update
                    if (pizzas.isEmpty()){
                        System.out.println("No orders to update");
                    } else {
                        System.out.print("Order number to update: ");
                        int orderNumber = getDigitInput(sc);
                        // prevent skipping the next input
                        sc.nextLine();
                        // input validation
                        while(!validateInput(orderNumber-1, quantities)) {
                            System.out.println("Order number doesn't exist" );
                            System.out.print("Order number to update: ");
                            orderNumber = getDigitInput(sc);
                            // prevent skipping the next input
                            sc.nextLine();
                        }
                        System.out.print("New quantity: ");
                        int newQuantity = getDigitInput(sc);
                        // prevent skipping the next input
                        sc.nextLine();
                        // input validation
                        while(!validateInput(newQuantity)) {
                            System.out.println("Quantity must be positive" );
                            System.out.print("New quantity: ");
                            newQuantity = getDigitInput(sc);
                            // prevent skipping the next input
                            sc.nextLine();
                        }
                        // update quantity
                        updateOrder(quantities, orderNumber-1, newQuantity);
                    }
                    break;
                }
                //Remove order
                case 3:
                {
                    // Check if there are existing orders to delete
                    if (pizzas.isEmpty()) {
                        System.out.println("No orders to remove");
                    } else {
                        System.out.print("Order number to remove: ");
                        int orderNumber = getDigitInput(sc);
                        // prevent skipping the next input
                        sc.nextLine();
                        // input validation
                        while(!validateInput(orderNumber-1, quantities)) {
                            System.out.println("Order number doesn't exist" );
                            System.out.print("Order number to remove: ");
                            orderNumber = getDigitInput(sc);
                            // prevent skipping the next input
                            sc.nextLine();
                        }
                        // delete order
                        removeOrder(pizzas, quantities, orderNumber-1);
                    }
                    break;
                }
                // List orders
                case 4:
                {
                    printOrders(pizzas, quantities);
                    break;
                }
                // Exit program
                case 5:
                {
                    keepOrdering = false;
                    System.out.println("---Thank you!---");
                    break;
                }
                // if user choice is not in the menu option
                default:
                    System.out.println("Invalid Option. Please choose again");
            }
            System.out.println();
        }

    }
}