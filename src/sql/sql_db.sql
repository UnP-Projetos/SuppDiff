create database suppdiffdb;

use suppdiffdb;

-- Table for Person
CREATE TABLE Person (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    phone VARCHAR(20),
    password VARCHAR(255) NOT NULL,
    birth_date DATE,
    CONSTRAINT UC_Person UNIQUE (email, cpf)
);

-- Table for Client
CREATE TABLE Client (
    id INT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES Person(id)
);

-- Table for Employee
CREATE TABLE Employee (
    id INT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES Person(id)
);

-- Table for Administrator
CREATE TABLE Administrator (
    id INT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES Person(id)
);

-- Table for Ticket (Chamado)
CREATE TABLE Ticket (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title varchar(100) NOT NULL,
    description TEXT NOT NULL,
    resolution VARCHAR(255),
    status VARCHAR(50) NOT NULL,
    client_id INT,
    employee_id INT,
    FOREIGN KEY (client_id) REFERENCES Client(id),
    FOREIGN KEY (employee_id) REFERENCES Employee(id)
);

INSERT INTO Person (name, email, cpf, phone, password, birth_date)
VALUES ('admin', 'admin', '00000000000', '123456789', 'admin', '1990-01-01');
INSERT INTO Administrator (id)
VALUES (1);
