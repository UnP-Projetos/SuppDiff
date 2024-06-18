create database suppdiff;

use suppdiff;

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

INSERT INTO Person (name, email, cpf, phone, password, birth_date)
VALUES ('admin', 'admin@admin.com', '00000000000', '123456789', 'admin', '1990-01-01');

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
    FOREIGN KEY (id) REFERENCES Employee(id)
);

-- Table for Ticket (Chamado)
CREATE TABLE Ticket (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title varchar(100) NOT NULL,
    description TEXT NOT NULL,
    status VARCHAR(50) NOT NULL,
    client_id INT,
    employee_id INT,
    FOREIGN KEY (client_id) REFERENCES Client(id),
    FOREIGN KEY (employee_id) REFERENCES Employee(id)
);

-- Table for TicketSystem (SistemaDeChamados)
CREATE TABLE TicketSystem (
    id INT AUTO_INCREMENT PRIMARY KEY
);

-- Many-to-Many relationship between TicketSystem and Client
CREATE TABLE TicketSystem_Client (
    ticket_system_id INT,
    client_id INT,
    PRIMARY KEY (ticket_system_id, client_id),
    FOREIGN KEY (ticket_system_id) REFERENCES TicketSystem(id),
    FOREIGN KEY (client_id) REFERENCES Client(id)
);

-- Many-to-Many relationship between TicketSystem and Employee
CREATE TABLE TicketSystem_Employee (
    ticket_system_id INT,
    employee_id INT,
    PRIMARY KEY (ticket_system_id, employee_id),
    FOREIGN KEY (ticket_system_id) REFERENCES TicketSystem(id),
    FOREIGN KEY (employee_id) REFERENCES Employee(id)
);

-- Many-to-Many relationship between TicketSystem and Ticket
CREATE TABLE TicketSystem_Ticket (
    ticket_system_id INT,
    ticket_id INT,
    PRIMARY KEY (ticket_system_id, ticket_id),
    FOREIGN KEY (ticket_system_id) REFERENCES TicketSystem(id),
    FOREIGN KEY (ticket_id) REFERENCES Ticket(id)
);
