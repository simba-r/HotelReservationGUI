import java.util.*;

public class Hotel {
    private List<Room> rooms = new ArrayList<>();

    public Hotel() {
        for (int i = 1; i <= 5; i++) rooms.add(new Room(i, "Standard"));
        for (int i = 6; i <= 8; i++) rooms.add(new Room(i, "Deluxe"));
        for (int i = 9; i <= 10; i++) rooms.add(new Room(i, "Suite"));
    }

    public List<Room> getAvailableRooms(String type) {
        List<Room> result = new ArrayList<>();
        for (Room r : rooms) {
            if (!r.isBooked() && r.getType().equalsIgnoreCase(type)) {
                result.add(r);
            }
        }
        return result;
    }

    public Room getRoomByNumber(int number) {
        for (Room r : rooms) {
            if (r.getNumber() == number) return r;
        }
        return null;
    }

    public List<Room> getAllRooms() { return rooms; }
}