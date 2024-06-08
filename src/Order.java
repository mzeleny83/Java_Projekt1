import java.io.Serializable;
import java.time.LocalDateTime;

public class Order implements Serializable {
    private int tableNumber;
    private Dish dish;
    private int quantity;
    private LocalDateTime orderedTime;
    private LocalDateTime fulfilmentTime;
    private boolean paid;

    public Order(int tableNumber, Dish dish, int quantity, LocalDateTime orderedTime) {
        this.tableNumber = tableNumber;
        this.dish = dish;
        this.quantity = quantity;
        this.orderedTime = orderedTime;
        this.fulfilmentTime = null;
        this.paid = false;
    }

    public Order(int tableNumber, Dish dish, int quantity, LocalDateTime orderedTime, LocalDateTime fulfilmentTime, boolean paid) {
        this.tableNumber = tableNumber;
        this.dish = dish;
        this.quantity = quantity;
        this.orderedTime = orderedTime;
        this.fulfilmentTime = fulfilmentTime;
        this.paid = paid;
    }

    // Getters and setters
    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getOrderedTime() {
        return orderedTime;
    }

    public void setOrderedTime(LocalDateTime orderedTime) {
        this.orderedTime = orderedTime;
    }

    public LocalDateTime getFulfilmentTime() {
        return fulfilmentTime;
    }

    public void setFulfilmentTime(LocalDateTime fulfilmentTime) {
        this.fulfilmentTime = fulfilmentTime;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
