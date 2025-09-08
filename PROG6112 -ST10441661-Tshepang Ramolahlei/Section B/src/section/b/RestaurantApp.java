package section.b;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RestaurantApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        MenuItem[] menu = {
            new Food("Burger", 5.99),
            new Food("Pizza", 7.49),
            new Food("Salad", 4.50),
            new Drink("Soda", 1.99),
            new Drink("Coffee", 2.49),
            new Drink("Water", 0.99)
        };

        Order order = new Order();
        int choice = -1;

        do {
            System.out.println("\n==== MENU ====");
            for (int i = 0; i < menu.length; i++) {
                System.out.printf("%d. %-10s - $%.2f\n", i + 1, menu[i].getName(), menu[i].getPrice());
            }
            System.out.println("0. Finish and Checkout");

            System.out.print("Select item by number: ");

            try {
                choice = scanner.nextInt();

                if (choice == 0) {
                    break;
                } else if (choice > 0 && choice <= menu.length) {
                    order.addItem(menu[choice - 1]);
                    System.out.println(menu[choice - 1].getName() + " added to your order.");
                } else {
                    System.out.println("⚠️ Invalid choice. Please enter a number between 0 and " + menu.length + ".");
                }

            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            }

        } while (true);

        if (order.getSubtotal() > 0) {
            order.printReceipt();
        } else {
            System.out.println("🛑 No items ordered. Thank you for visiting!");
        }
    }
}
