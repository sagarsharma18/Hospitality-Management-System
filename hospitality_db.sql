CREATE DATABASE IF NOT EXISTS hospitality_db;
USE hospitality_db;

CREATE TABLE IF NOT EXISTS hotels (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    location VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS rooms (
    id INT AUTO_INCREMENT PRIMARY KEY,
    hotel_id INT,
    type VARCHAR(50),
    price DOUBLE,
    status VARCHAR(20),
    FOREIGN KEY (hotel_id) REFERENCES hotels(id)
);

CREATE TABLE IF NOT EXISTS guests (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    phone VARCHAR(15),
    email VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS reservations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    guest_id INT,
    room_id INT,
    checkin DATE,
    checkout DATE,
    FOREIGN KEY (guest_id) REFERENCES guests(id),
    FOREIGN KEY (room_id) REFERENCES rooms(id)
);
