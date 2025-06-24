import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    private static Hotel hotel = new Hotel();
    private static BookingManager bookingManager = new BookingManager();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Hotel Reservation System");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);

        JComboBox<String> categoryBox = new JComboBox<>(new String[]{"Standard", "Deluxe", "Suite"});
        JTextField nameField = new JTextField(10);
        JTextField roomField = new JTextField(5);

        JButton showButton = new JButton("Show Available Rooms");
        JButton bookButton = new JButton("Book Room");
        JButton cancelButton = new JButton("Cancel Booking");
        JButton showBookings = new JButton("Show All Bookings");

        showButton.addActionListener(e -> {
            String category = (String) categoryBox.getSelectedItem();
            displayArea.setText("Available " + category + " Rooms:\n");
            for (Room r : hotel.getAvailableRooms(category)) {
                displayArea.append(r + "\n");
            }
        });

        bookButton.addActionListener(e -> {
            String name = nameField.getText();
            int roomNo;
            try {
                roomNo = Integer.parseInt(roomField.getText());
                Room room = hotel.getRoomByNumber(roomNo);
                if (room != null && !room.isBooked()) {
                    room.book();
                    bookingManager.addBooking(new Booking(name, room));
                    displayArea.setText("Booked successfully for " + name + " in Room " + roomNo);
                } else {
                    displayArea.setText("Room unavailable or doesn't exist.");
                }
            } catch (NumberFormatException ex) {
                displayArea.setText("Invalid room number.");
            }
        });

        cancelButton.addActionListener(e -> {
            try {
                int roomNo = Integer.parseInt(roomField.getText());
                if (bookingManager.cancelBooking(roomNo)) {
                    displayArea.setText("Booking canceled for Room " + roomNo);
                } else {
                    displayArea.setText("No booking found for Room " + roomNo);
                }
            } catch (NumberFormatException ex) {
                displayArea.setText("Invalid room number.");
            }
        });

        showBookings.addActionListener(e -> {
            displayArea.setText("Current Bookings:\n");
            for (Booking b : bookingManager.getAllBookings()) {
                displayArea.append(b + "\n");
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Room Type:"));
        panel.add(categoryBox);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Room #:"));
        panel.add(roomField);
        panel.add(showButton);
        panel.add(bookButton);
        panel.add(cancelButton);
        panel.add(showBookings);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(displayArea), BorderLayout.CENTER);
        frame.setVisible(true);
    }
}