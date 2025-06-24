import java.util.*;

public class BookingManager {
    private List<Booking> bookings = new ArrayList<>();

    public void addBooking(Booking b) { bookings.add(b); }

    public boolean cancelBooking(int roomNumber) {
        Iterator<Booking> it = bookings.iterator();
        while (it.hasNext()) {
            Booking b = it.next();
            if (b.getRoom().getNumber() == roomNumber) {
                b.getRoom().cancel();
                it.remove();
                return true;
            }
        }
        return false;
    }

    public List<Booking> getAllBookings() {
        return bookings;
    }
}