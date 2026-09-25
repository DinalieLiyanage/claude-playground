package booking;

public class Event {
    private final String id;
    private final String name;
    private final double price;
    private int availableSeats;

    public Event(String id, String name, double price, int availableSeats) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.availableSeats = availableSeats;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getAvailableSeats() { return availableSeats; }

    public void reserveSeats(int count) {
        availableSeats -= count;
    }

    public void releaseSeats(int count) {
        availableSeats += count;
    }

    @Override
    public String toString() {
        return String.format("[%s] %-20s  $%6.2f  seats left: %d", id, name, price, availableSeats);
    }
}
