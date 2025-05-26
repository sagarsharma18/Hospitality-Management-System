# Hospitality Management System

This is a Java-based desktop application developed during my 8-week Core Java internship at Internshala. It is designed to manage hotel operations like room booking, guest management, and reservation tracking using Java Swing and MySQL.

## Features

- Add, update, and view hotel details  
- Room management with availability status  
- Guest registration and tracking  
- Reservation system with date tracking  
- GUI developed using Java Swing  
- Database integration using JDBC and MySQL

## Technologies Used

- Java (Core Java, OOP, Swing)  
- MySQL  
- JDBC  
- Apache NetBeans  
- XAMPP (for MySQL Server)

## Database Structure

- `hotels` table  
- `rooms` table  
- `guests` table  
- `reservations` table

## How to Run

1. Start MySQL from XAMPP Control Panel.  
2. Import `hospitality_db.sql` in phpMyAdmin.  
3. Open the project in NetBeans.  
4. Run the main file `HospitalitySystem.java`.  
5. Ensure the database connection string is correct:  
```java
conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/hospitality_db", "root", "root123");
