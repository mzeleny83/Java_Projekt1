import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantManager {
    private List<Dish> dishes;
    private List<Order> orders;

    public RestaurantManager() {
        dishes = new ArrayList<>();
        orders = new ArrayList<>();
    }

    // Load data from files
    public void loadData(String dishesFilePath, String ordersFilePath) {
        try (ObjectInputStream dishInputStream = new ObjectInputStream(new FileInputStream(dishesFilePath));
             ObjectInputStream orderInputStream = new ObjectInputStream(new FileInputStream(ordersFilePath))) {

            dishes = (List<Dish>) dishInputStream.readObject();
            orders = (List<Order>) orderInputStream.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("Files not found, starting with empty data.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
    }

    // Save data to files
    public void saveData(String dishesFilePath, String ordersFilePath) {
        try (ObjectOutputStream dishOutputStream = new ObjectOutputStream(new FileOutputStream(dishesFilePath));
             ObjectOutputStream orderOutputStream = new ObjectOutputStream(new FileOutputStream(ordersFilePath))) {

            dishOutputStream.writeObject(dishes);
            orderOutputStream.writeObject(orders);
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    // Add dish
    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    // Remove dish
    public void removeDish(Dish dish) {
        dishes.remove(dish);
    }

    // Add order
    public void addOrder(Order order) {
        orders.add(order);
    }

    // Get number of pending orders
    public long getPendingOrdersCount() {
        return orders.stream().filter(order -> order.getFulfilmentTime() == null).count();
    }

    // Sort orders by order time
    public List<Order> sortOrdersByTime() {
        return orders.stream().sorted(Comparator.comparing(Order::getOrderedTime)).collect(Collectors.toList());
    }

    // Get average order processing time in minutes
    public double getAverageProcessingTime() {
        return orders.stream()
                .filter(order -> order.getFulfilmentTime() != null)
                .mapToLong(order -> Duration.between(order.getOrderedTime(), order.getFulfilmentTime()).toMinutes())
                .average()
                .orElse(0);
    }

    // Get list of dishes ordered today
    public List<Dish> getDishesOrderedToday() {
        LocalDateTime today = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        return orders.stream()
                .filter(order -> order.getOrderedTime().isAfter(today))
                .map(Order::getDish)
                .distinct()
                .collect(Collectors.toList());
    }

    // Get orders for a specific table
    public List<Order> getOrdersForTable(int tableNumber) {
        return orders.stream()
                .filter(order -> order.getTableNumber() == tableNumber)
                .collect(Collectors.toList());
    }

    // Print orders for a specific table
    public void printOrdersForTable(int tableNumber) {
        List<Order> tableOrders = getOrdersForTable(tableNumber);
        System.out.println("** Objednávky pro stůl č. " + (tableNumber < 10 ? " " + tableNumber : tableNumber) + " **");
        System.out.println("****");
        for (int i = 0; i < tableOrders.size(); i++) {
            Order order = tableOrders.get(i);
            System.out.println((i + 1) + ". " + order.getDish().getTitle() + " " + order.getQuantity() + "x (" + (order.getDish().getPrice() * order.getQuantity()) + " Kč):\t"
                    + order.getOrderedTime().toLocalTime() + "-"
                    + (order.getFulfilmentTime() != null ? order.getFulfilmentTime().toLocalTime() : "") + "\t"
                    + (order.isPaid() ? "zaplaceno" : ""));
        }
        System.out.println("******");
    }
}
