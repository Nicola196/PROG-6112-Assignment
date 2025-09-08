package section.b;

import java.util.ArrayList;

public class Order {
    private ArrayList<MenuItem> items = new ArrayList<>();
    private final double TAX_RATE = 0.1; // 10%

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public double getSubtotal() {
        double sum = 0;
        for (MenuItem item : items) {
            sum += item.getPrice();
        }
        return sum;
    }

    public double getTax() {
        return getSubtotal() * TAX_RATE;
    }

    public double getTotal() {
        return getSubtotal() + getTax();
    }

    public void printReceipt() {
        System.out.println("===== ORDER SUMMARY =====");
        for (MenuItem item : items) {
            System.out.printf("%-10s | %-15s : $%.2f\n", item.getType(), item.getName(), item.getPrice());
        }
        System.out.println("--------------------------");
        System.out.printf("Subtotal: $%.2f\n", getSubtotal());
        System.out.printf("Tax (10%%): $%.2f\n", getTax());
        System.out.printf("Total:    $%.2f\n", getTotal());
    }
}
