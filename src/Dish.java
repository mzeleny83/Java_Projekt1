import java.io.Serializable;

public class Dish implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private double price;
    private int preparationTime; // in minutes
    private String image;

    public Dish(String title, double price, int preparationTime, String image) {
        if (preparationTime <= 0) {
            throw new IllegalArgumentException("Preparation time must be positive.");
        }
        this.title = title;
        this.price = price;
        this.preparationTime = preparationTime;
        this.image = (image == null || image.isEmpty()) ? "blank" : image;
    }

    // Getters and Setters
    public String getTitle() { return title; }
    public double getPrice() { return price; }
    public int getPreparationTime() { return preparationTime; }
    public String getImage() { return image; }

    @Override
    public String toString() {
        return title + " (" + price + " Kč)";
    }
}
