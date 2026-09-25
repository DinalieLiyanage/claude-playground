package booking;

public class Booking {
    private final String bookingId;
    private final String customerName;
    private final Event event;
    private final int seats;
    private boolean cancelled;

    public Booking(String bookingId, String customerName, Event event, int seats) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.event = event;
        this.seats = seats;
    }

    public String getBookingId() { return bookingId; }
    public String getCustomerName() { return customerName; }
    public Event getEvent() { return event; }
    public int getSeats() { return seats; }
    public boolean isCancelled() { return cancelled; }

    public double getTotal() {
        return event.getPrice() * seats;
    }

    public void cancel() {
        cancelled = true;
    }

    @Override
    public String toString() {
        return String.format("%s | %-8s | %-20s | %d seat(s) | $%.2f%s",
                bookingId, customerName, event.getName(), seats, getTotal(),
                cancelled ? " | CANCELLED" : "");
    }
}
