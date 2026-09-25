# Ticket Booking Demo

A small Java console app that demonstrates a ticket booking scenario. Everything is kept in memory; there is no database.

## What it shows

- Listing events with price and available seats
- Booking seats (success)
- Rejected bookings: sold out, not enough seats, unknown event
- Cancelling a booking and releasing its seats
- A final summary with total revenue

## Structure

```
src/booking/
  Event.java           an event with price and seat count
  Booking.java         a customer's booking
  BookingService.java  in-memory booking logic
  Main.java            runs the demo scenario
```

## Run

From this folder (Java 17+):

```
javac -d out src/booking/*.java
java -cp out booking.Main
```
