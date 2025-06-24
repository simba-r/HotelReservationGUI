public class Room {
    private int number;
    private String type;
    private boolean booked;

    public Room(int number, String type) {
        this.number = number;
        this.type = type;
        this.booked = false;
    }

    public int getNumber() { return number; }
    public String getType() { return type; }
    public boolean isBooked() { return booked; }

    public void book() { booked = true; }
    public void cancel() { booked = false; }

    @Override
    public String toString() {
        return "Room " + number + " (" + type + ") - " + (booked ? "Booked" : "Available");
    }
}