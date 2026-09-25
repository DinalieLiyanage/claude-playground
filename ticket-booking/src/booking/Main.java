package booking;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Ticket Booking Demo ===");

        BookingService service = new BookingService();
        service.addEvent(new Event("E1", "Rock Concert", 75.00, 5));
        service.addEvent(new Event("E2", "Movie Premiere", 20.00, 10));
        service.addEvent(new Event("E3", "Football Final", 120.00, 2));

        service.listEvents();

        // Successful bookings
        service.book("Alice", "E1", 2);
        service.book("Bob", "E2", 4);
        service.book("Charlie", "E3", 2);

        // Failing bookings
        service.book("Dave", "E3", 1);   // sold out
        service.book("Eve", "E1", 10);   // not enough seats
        service.book("Frank", "E9", 1);  // unknown event

        // Cancel, then the freed seats can be booked again
        service.cancel("BK003");
        service.book("Dave", "E3", 1);
        service.cancel("BK003");         // already cancelled

        service.listEvents();
        service.printSummary();
    }
}
