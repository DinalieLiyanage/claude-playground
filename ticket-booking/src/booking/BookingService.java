package booking;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Keeps everything in memory; no database. */
public class BookingService {
    private final Map<String, Event> events = new LinkedHashMap<>();
    private final Map<String, Booking> bookings = new LinkedHashMap<>();
    private int nextBookingNumber = 1;

    public void addEvent(Event event) {
        events.put(event.getId(), event);
    }

    public void listEvents() {
        System.out.println("\n--- Available Events ---");
        for (Event event : events.values()) {
            System.out.println(event);
        }
    }

    public Booking book(String customerName, String eventId, int seats) {
        System.out.println("\n> " + customerName + " requests " + seats + " seat(s) for " + eventId);

        Event event = events.get(eventId);
        if (event == null) {
            System.out.println("  FAILED: event " + eventId + " not found.");
            return null;
        }
        if (seats <= 0) {
            System.out.println("  FAILED: seat count must be positive.");
            return null;
        }
        if (seats > event.getAvailableSeats()) {
            System.out.println("  FAILED: only " + event.getAvailableSeats() + " seat(s) left for " + event.getName() + ".");
            return null;
        }

        event.reserveSeats(seats);
        String bookingId = String.format("BK%03d", nextBookingNumber++);
        Booking booking = new Booking(bookingId, customerName, event, seats);
        bookings.put(bookingId, booking);

        System.out.printf("  SUCCESS: booking %s confirmed. Total: $%.2f%n", bookingId, booking.getTotal());
        return booking;
    }

    public void cancel(String bookingId) {
        System.out.println("\n> Cancel request for " + bookingId);

        Booking booking = bookings.get(bookingId);
        if (booking == null) {
            System.out.println("  FAILED: booking " + bookingId + " not found.");
            return;
        }
        if (booking.isCancelled()) {
            System.out.println("  FAILED: booking " + bookingId + " is already cancelled.");
            return;
        }

        booking.cancel();
        booking.getEvent().releaseSeats(booking.getSeats());
        System.out.printf("  SUCCESS: booking %s cancelled. Refund: $%.2f%n", bookingId, booking.getTotal());
    }

    public List<Booking> getBookings() {
        return new ArrayList<>(bookings.values());
    }

    public void printSummary() {
        System.out.println("\n--- Booking Summary ---");
        double revenue = 0;
        for (Booking booking : bookings.values()) {
            System.out.println(booking);
            if (!booking.isCancelled()) {
                revenue += booking.getTotal();
            }
        }
        System.out.printf("Total revenue: $%.2f%n", revenue);
    }
}
