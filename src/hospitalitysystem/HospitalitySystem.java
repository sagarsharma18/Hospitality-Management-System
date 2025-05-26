package hospitalitysystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class HospitalitySystem extends JFrame {
    Connection conn;

    JTabbedPane tabs;

    // Hotel tab fields
    JTextField hotelName = new JTextField();
    JTextField hotelLocation = new JTextField();
    JTextArea hotelArea = new JTextArea();

    // Room tab fields
    JTextField roomHotelId = new JTextField();
    JTextField roomType = new JTextField();
    JTextField roomPrice = new JTextField();
    JTextField roomStatus = new JTextField();
    JTextArea roomArea = new JTextArea();

    // Guest tab fields
    JTextField guestName = new JTextField();
    JTextField guestPhone = new JTextField();
    JTextField guestEmail = new JTextField();
    JTextArea guestArea = new JTextArea();

    // Reservation tab fields
    JTextField resGuestId = new JTextField();
    JTextField resRoomId = new JTextField();
    JTextField resCheckIn = new JTextField();
    JTextField resCheckOut = new JTextField();
    JTextArea resArea = new JTextArea();

    public HospitalitySystem() {
        setTitle("Hospitality Management System");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tabs = new JTabbedPane();
        tabs.add("Hotels", hotelPanel());
        tabs.add("Rooms", roomPanel());
        tabs.add("Guests", guestPanel());
        tabs.add("Reservations", reservationPanel());
        add(tabs);

        connectDB();
        setVisible(true);
    }

    void connectDB() {
        try {
             Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/hospitality_db", "root", "root123");
            JOptionPane.showMessageDialog(this, "Connected to MySQL DB");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        }
    }

    JPanel hotelPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(3, 2));
        form.add(new JLabel("Hotel Name:"));
        form.add(hotelName);
        form.add(new JLabel("Location:"));
        form.add(hotelLocation);

        JButton add = new JButton("Add Hotel");
        JButton view = new JButton("View Hotels");
        form.add(add);
        form.add(view);

        panel.add(form, BorderLayout.NORTH);
        hotelArea.setEditable(false);
        panel.add(new JScrollPane(hotelArea), BorderLayout.CENTER);

        add.addActionListener(e -> {
            try {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO hotels(name, location) VALUES (?, ?)");
                ps.setString(1, hotelName.getText());
                ps.setString(2, hotelLocation.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Hotel Added");
                hotelName.setText("");
                hotelLocation.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        view.addActionListener(e -> {
            hotelArea.setText("");
            try {
                ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM hotels");
                while (rs.next()) {
                    hotelArea.append("ID: " + rs.getInt("id") + " | Name: " + rs.getString("name") +
                            " | Location: " + rs.getString("location") + "\n");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        return panel;
    }

    JPanel roomPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(5, 2));
        form.add(new JLabel("Hotel ID:"));
        form.add(roomHotelId);
        form.add(new JLabel("Room Type:"));
        form.add(roomType);
        form.add(new JLabel("Price:"));
        form.add(roomPrice);
        form.add(new JLabel("Status:"));
        form.add(roomStatus);

        JButton add = new JButton("Add Room");
        JButton view = new JButton("View Rooms");
        form.add(add);
        form.add(view);

        panel.add(form, BorderLayout.NORTH);
        roomArea.setEditable(false);
        panel.add(new JScrollPane(roomArea), BorderLayout.CENTER);

        add.addActionListener(e -> {
            try {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO rooms(hotel_id, type, price, status) VALUES (?, ?, ?, ?)");
                ps.setInt(1, Integer.parseInt(roomHotelId.getText()));
                ps.setString(2, roomType.getText());
                ps.setDouble(3, Double.parseDouble(roomPrice.getText()));
                ps.setString(4, roomStatus.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Room Added");
                roomHotelId.setText("");
                roomType.setText("");
                roomPrice.setText("");
                roomStatus.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        view.addActionListener(e -> {
            roomArea.setText("");
            try {
                ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM rooms");
                while (rs.next()) {
                    roomArea.append("ID: " + rs.getInt("id") +
                            " | Hotel ID: " + rs.getInt("hotel_id") +
                            " | Type: " + rs.getString("type") +
                            " | Price: " + rs.getDouble("price") +
                            " | Status: " + rs.getString("status") + "\n");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        return panel;
    }

    JPanel guestPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(4, 2));
        form.add(new JLabel("Guest Name:"));
        form.add(guestName);
        form.add(new JLabel("Phone:"));
        form.add(guestPhone);
        form.add(new JLabel("Email:"));
        form.add(guestEmail);

        JButton add = new JButton("Add Guest");
        JButton view = new JButton("View Guests");
        form.add(add);
        form.add(view);

        panel.add(form, BorderLayout.NORTH);
        guestArea.setEditable(false);
        panel.add(new JScrollPane(guestArea), BorderLayout.CENTER);

        add.addActionListener(e -> {
            try {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO guests(name, phone, email) VALUES (?, ?, ?)");
                ps.setString(1, guestName.getText());
                ps.setString(2, guestPhone.getText());
                ps.setString(3, guestEmail.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Guest Added");
                guestName.setText("");
                guestPhone.setText("");
                guestEmail.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        view.addActionListener(e -> {
            guestArea.setText("");
            try {
                ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM guests");
                while (rs.next()) {
                    guestArea.append("ID: " + rs.getInt("id") +
                            " | Name: " + rs.getString("name") +
                            " | Phone: " + rs.getString("phone") +
                            " | Email: " + rs.getString("email") + "\n");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        return panel;
    }

    JPanel reservationPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(5, 2));
        form.add(new JLabel("Guest ID:"));
        form.add(resGuestId);
        form.add(new JLabel("Room ID:"));
        form.add(resRoomId);
        form.add(new JLabel("Check-In (yyyy-mm-dd):"));
        form.add(resCheckIn);
        form.add(new JLabel("Check-Out (yyyy-mm-dd):"));
        form.add(resCheckOut);

        JButton add = new JButton("Add Reservation");
        JButton view = new JButton("View Reservations");
        form.add(add);
        form.add(view);

        panel.add(form, BorderLayout.NORTH);
        resArea.setEditable(false);
        panel.add(new JScrollPane(resArea), BorderLayout.CENTER);

        add.addActionListener(e -> {
            try {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO reservations(guest_id, room_id, checkin, checkout) VALUES (?, ?, ?, ?)");
                ps.setInt(1, Integer.parseInt(resGuestId.getText()));
                ps.setInt(2, Integer.parseInt(resRoomId.getText()));
                ps.setDate(3, Date.valueOf(resCheckIn.getText()));
                ps.setDate(4, Date.valueOf(resCheckOut.getText()));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Reservation Added");
                resGuestId.setText("");
                resRoomId.setText("");
                resCheckIn.setText("");
                resCheckOut.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        view.addActionListener(e -> {
            resArea.setText("");
            try {
                ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM reservations");
                while (rs.next()) {
                    resArea.append("ID: " + rs.getInt("id") +
                            " | Guest ID: " + rs.getInt("guest_id") +
                            " | Room ID: " + rs.getInt("room_id") +
                            " | Check-In: " + rs.getDate("checkin") +
                            " | Check-Out: " + rs.getDate("checkout") + "\n");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        return panel;
    }

    public static void main(String[] args) {
        new HospitalitySystem();
    }
}
