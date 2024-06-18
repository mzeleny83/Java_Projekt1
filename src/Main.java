import java.io.*;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        RestaurantManager manager = new RestaurantManager();

        // 1. Load state from disk
        manager.loadData("dishes.dat", "orders.dat");

        // 2. Prepare test data
        // Add dishes
        Dish dish1 = new Dish("Kuřecí řízek obalovaný 150 g", 150, 30, "kureci-rizek-01");
        Dish dish2 = new Dish("Hranolky 150 g", 50, 10, "hranolky-01");
        Dish dish3 = new Dish("Pstruh na víně 200 g", 250, 40, "pstruh-na-vine-01");
        Dish dish4 = new Dish("Kofola 0,5 l", 30, 2, "kofola-01");
        manager.addDish(dish1);
        manager.addDish(dish2);
        manager.addDish(dish3);
        manager.addDish(dish4);

        // Add orders
        Order order1 = new Order(15, dish1, 2, LocalDateTime.now());
        Order order2 = new Order(15, dish2, 2, LocalDateTime.now());
        Order order3 = new Order(15, dish4, 2, LocalDateTime.now(), LocalDateTime.now(), true);
        manager.addOrder(order1);
        manager.addOrder(order2);
        manager.addOrder(order3);

        // 3. Print total consumption cost for table 15
        System.out.println("Total cost for table 15: " + manager.getOrdersForTable(15).stream()
                .mapToInt(order -> (int) (order.getDish().getPrice() * order.getQuantity()))
                .sum() + " Kč");

        // 4. Use all prepared methods to get information for management
        // Number of pending orders
        System.out.println("Pending orders count: " + manager.getPendingOrdersCount());

        // Sort orders by order time
        List<Order> sortedOrders = manager.sortOrdersByTime();
        System.out.println("Sorted orders by time:");
        sortedOrders.forEach(order -> System.out.println(order.getOrderedTime()));

        // Average order processing time
        System.out.println("Average processing time: " + manager.getAverageProcessingTime() + " minutes");

        // List of dishes ordered today
        List<Dish> dishesOrderedToday = manager.getDishesOrderedToday();
        System.out.println("Dishes ordered today:");
        dishesOrderedToday.forEach(dish -> System.out.println(dish.getTitle()));

        // Export orders for a specific table
        System.out.println("Exported orders for table 15:");
        manager.printOrdersForTable(15);

        // Save modified data to disk
        manager.saveData("dishes.dat", "orders.dat");
        System.out.println("Finish the program:");
    }
}
