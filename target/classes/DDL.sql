
drop database if exists aerolinea;

create database if not exists aerolinea;

use aerolinea;

CREATE TABLE rol(
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(40) NOT NULL
);

CREATE table usuario(
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(200) NOT NULL,
    rol_id INT,
    FOREIGN KEY (rol_id) REFERENCES rol (id) ON DELETE SET NULL
);

CREATE TABLE documentTypes(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL
);

CREATE TABLE customers(
    id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    age INT NOT NULL,
    idDocType INT,
    FOREIGN KEY (idDocType) REFERENCES documentTypes(id) ON DELETE SET NULL
);

CREATE TABLE flightFares(
    id INT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(20) NOT NULL,
    details TEXT NOT NULL,
    value DOUBLE(7,3) NOT NULL
);

CREATE TABLE countries(
    id VARCHAR(5) PRIMARY KEY,
    name VARCHAR(30) NOT NULL
);

CREATE TABLE cities(
    id VARCHAR(5) PRIMARY KEY,
    name VARCHAR(40) NOT NULL,
    idCountry VARCHAR(5),
    FOREIGN KEY (idCountry) REFERENCES countries(id) ON DELETE SET NULL
);

CREATE TABLE airports(
    id VARCHAR(5) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    idCity VARCHAR(5),
    FOREIGN KEY (idCity) REFERENCES cities(id) ON DELETE SET NULL
);

CREATE TABLE trips (
    id INT PRIMARY KEY AUTO_INCREMENT,
    date DATE NOT NULL,
    price DOUBLE,
    originAirport VARCHAR(5) NOT NULL,
    destinationAirport VARCHAR(5) NOT NULL,
    FOREIGN KEY (originAirport) REFERENCES airports(id),
    FOREIGN KEY (destinationAirport) REFERENCES airports(id)
);

CREATE TABLE tripBooking(
    id INT PRIMARY KEY AUTO_INCREMENT,
    date DATE NOT NULL,
    idTrips INT,
    FOREIGN KEY (idTrips) REFERENCES trips(id) ON DELETE SET NULL
);

CREATE TABLE tripBookingDetails(
    id INT PRIMARY KEY AUTO_INCREMENT,
    idTripBooking INT,
    idCustomers VARCHAR(20),
    idFlightFares INT,
    FOREIGN KEY (idTripBooking) REFERENCES tripBooking(id) ON DELETE SET NULL,
    FOREIGN KEY (idCustomers) REFERENCES customers(id) ON DELETE SET NULL,
    FOREIGN KEY (idFlightFares) REFERENCES flightFares(id) ON DELETE SET NULL
);

CREATE TABLE airlines(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL
);

CREATE TABLE tripulationRoles(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL
);



CREATE TABLE employees(
    id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(40) NOT NULL,
    idRol INT,
    ingressDate DATE NOT NULL,
    idAirline INT,
    idAirport VARCHAR(5),
    FOREIGN KEY (idRol) REFERENCES tripulationRoles(id) ON DELETE SET NULL,
    FOREIGN KEY (idAirline) REFERENCES airlines(id) ON DELETE SET NULL,
    FOREIGN KEY (idAirport) REFERENCES airports(id) ON DELETE SET NULL
);

CREATE TABLE revision_details(
    id VARCHAR(20) PRIMARY KEY,
    description TEXT NOT NULL,
    idEmployee VARCHAR(20),
    FOREIGN KEY (idEmployee) REFERENCES employees(id) ON DELETE SET NULL
);

CREATE TABLE manufacturers(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL
);

CREATE TABLE models(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    manufacturerID INT,
    FOREIGN KEY (manufacturerID) REFERENCES manufacturers(id) ON DELETE SET NULL
);

CREATE TABLE status(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL
);

CREATE TABLE planes(
    id INT PRIMARY KEY AUTO_INCREMENT,
    plates VARCHAR(30) NOT NULL,
    capacity INT NOT NULL,
    fabricationDate DATE NOT NULL,
    idStatus INT,
    idModel INT,
    idAirline INT,
    FOREIGN KEY(idStatus) REFERENCES status(id) ON DELETE SET NULL,
    FOREIGN KEY (idModel) REFERENCES models(id) ON DELETE SET NULL,
    FOREIGN KEY (idAirline) REFERENCES airlines(id) ON DELETE SET NULL
);

CREATE TABLE revisions(
    id INT PRIMARY KEY AUTO_INCREMENT,
    revisionDate DATE NOT NULL,
    idPlane INT,
    FOREIGN KEY(idPlane) REFERENCES planes(id) ON DELETE SET NULL
);

CREATE TABLE revEmployee(
    idEmployee VARCHAR(20),
    idRevision INT,
    PRIMARY KEY(idEmployee,idRevision),
    FOREIGN KEY (idEmployee) REFERENCES employees(id) ON DELETE CASCADE,
    FOREIGN KEY (idRevision) REFERENCES revisions(id) ON DELETE CASCADE
);

CREATE TABLE gates(
    id INT PRIMARY KEY AUTO_INCREMENT,
    number VARCHAR(10) NOT NULL,
    idAirport VARCHAR(5),
    FOREIGN KEY (idAirport) REFERENCES airports(id) ON DELETE SET NULL
);

CREATE TABLE flightConnections ( 
id INT PRIMARY KEY AUTO_INCREMENT,
 numConnection VARCHAR(20) NOT NULL, 
 idTrip INT NOT NULL, idPlane INT NOT NULL, 
 departureAirport VARCHAR(5) NOT NULL, arrivalAirport VARCHAR(5) NOT NULL, 
 departureTime DATETIME NOT NULL, arrivalTime DATETIME NOT NULL,
 orderNumber INT NOT NULL, 
 connectionType ENUM('DIRECT', 'LAYOVER') NOT NULL, 
 FOREIGN KEY (idTrip) REFERENCES trips(id), 
 FOREIGN KEY (idPlane) REFERENCES planes(id), 
 FOREIGN KEY (departureAirport) REFERENCES airports(id), 
 FOREIGN KEY (arrivalAirport) REFERENCES airports(id) 
 );

CREATE TABLE tripCrews(
    idEmployee VARCHAR(20),
    idConnection INT,
    PRIMARY KEY(idEmployee,idConnection),
    FOREIGN KEY (idEmployee) REFERENCES employees(id) ON DELETE CASCADE,
    FOREIGN KEY (idConnection) REFERENCES flightConnections(id) ON DELETE CASCADE
);

CREATE TABLE user(
    id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    surname VARCHAR(20) NOT NULL,
    email VARCHAR(20) NOT NULL,
    idRol INT,
    FOREIGN KEY (idRol) REFERENCES tripulationRoles(id) ON DELETE SET NULL
);

INSERT into rol (nombre) VALUES 
("administrador"),
("agente de ventas"),
("tecnico de mantenimiento"),
("cliente");

inserT into usuario ( nombre, email, password, rol_id) VALUES
	("diego","diegodfp.fly@gmail.com","123", 1);

INSERT INTO manufacturers (name) VALUES
  ('Airbus'),
  ('Boeing'),
  ('Embraer'),
  ('Bombardier');

INSERT INTO models (name, manufacturerID) VALUES
  ('A320', (SELECT id FROM manufacturers WHERE name = 'Airbus')),
  ('737', (SELECT id FROM manufacturers WHERE name = 'Boeing')),
  ('E190', (SELECT id FROM manufacturers WHERE name = 'Embraer')),
  ('Q400', (SELECT id FROM manufacturers WHERE name = 'Bombardier')),
  ('A350', (SELECT id FROM manufacturers WHERE name = 'Airbus')),
  ('787', (SELECT id FROM manufacturers WHERE name = 'Boeing'));

  INSERT INTO status (name) VALUES
  ('Activo'),
  ('En Mantenimiento'),
  ('Retirado');
  
INSERT INTO airlines (name) VALUES
('Aerolineas Argentinas'),
('LATAM Airlines'),
('American Airlines'),
('Iberia'),
('Avianca'),
('Delta Air Lines'),
('United Airlines'),
('Air France'),
('Lufthansa'),
('Emirates');
-- INSERT PLANES
INSERT INTO planes (plates, capacity, fabricationDate, idStatus, idModel, idAirline) VALUES
-- Aerolineas Argentinas
('LV-ABC', 200, '2015-03-15', 1, 1, 1),
('LV-DEF', 180, '2016-05-20', 1, 2, 1),

-- LATAM Airlines
('CC-BAA', 220, '2017-01-10', 1, 3, 2),
('CC-BBB', 190, '2018-07-05', 1, 1, 2),

-- American Airlines
('N123AA', 300, '2014-11-30', 1, 1, 3),
('N456AA', 250, '2016-09-22', 1, 2, 3),

-- Iberia
('EC-IZD', 280, '2013-08-18', 1,3, 4),
('EC-JBA', 310, '2015-12-03', 1, 4, 4),

-- Avianca
('N589AV', 210, '2019-02-14', 1, 2, 5),
('N590AV', 230, '2020-06-30', 1, 3, 5),

-- Delta Air Lines
('N110DL', 320, '2017-04-25', 1, 1, 6),
('N220DL', 290, '2018-10-11', 1, 5, 6),

-- United Airlines
('N33286', 260, '2016-07-19', 1, 5, 7),
('N37281', 240, '2018-03-08', 1, 4, 7),

-- Air France
('F-GZNA', 340, '2015-09-01', 1, 1, 8),
('F-HPJD', 300, '2017-11-20', 1, 2, 8),

-- Lufthansa
('D-AIMA', 330, '2014-05-12', 1, 2, 9),
('D-AIMB', 350, '2016-08-23', 1, 3, 9),

-- Emirates
('A6-EGA', 380, '2015-01-07', 1, 4, 10),
('A6-EGB', 400, '2017-06-16', 1, 5, 10);
 -- 
 use aerolinea;
 
-- Insertar tipos de documento
INSERT INTO documentTypes (name) VALUES
    ('Pasaporte'),
    ('DNI'),
    ('Tarjeta de Residencia');

-- Insertar clientes
INSERT INTO customers (id, name, age, idDocType) VALUES
    ('C12345', 'Juan Pérez', 30, (SELECT id FROM documentTypes WHERE name = 'DNI')),
    ('C67890', 'María López', 25, (SELECT id FROM documentTypes WHERE name = 'Pasaporte'));

-- Insertar tarifas de vuelo
INSERT INTO flightFares (description, details, value) VALUES
    ('Economy', 'Clase económica', 200.00),
    ('Business', 'Clase ejecutiva', 500.00);



-- Insertar reservas de viaje
INSERT INTO tripBooking (date, idTrips) VALUES
    ('2024-07-20', (SELECT id FROM trips WHERE date = '2024-07-30')),
    ('2024-08-01', (SELECT id FROM trips WHERE date = '2024-08-15'));

-- Insertar detalles de reserva de viaje
INSERT INTO tripBookingDetails (idTripBooking, idCustomers, idFlightFares) VALUES
    ((SELECT id FROM tripBooking WHERE date = '2024-07-20'), 'C12345', (SELECT id FROM flightFares WHERE description = 'Economy')),
    ((SELECT id FROM tripBooking WHERE date = '2024-08-01'), 'C67890', (SELECT id FROM flightFares WHERE description = 'Business'));


-- Insertar roles de tripulación
INSERT INTO tripulationRoles (name) VALUES
    ('Piloto'),
    ('Copiloto'),
    ('Azafata'),
    ('Ingeniero');

-- Insertar países

INSERT INTO countries (id, name) VALUES
	('AR','Argentina'),
    ('BR','Brasil'),
    ('US', 'United States'),
    ('ES', 'España'),
    ('MX', 'México'),
    ('CO', 'Colombia');

-- Insertar ciudades
INSERT INTO cities (id, name, idCountry) VALUES
	('BUE', 'Buenos Aires', 'AR'),
    ('COR', 'Córdoba', 'AR'),
    ('ROA', 'Rosario', 'AR'),
    ('MEN', 'Mendoza', 'AR'),
    ('TUC', 'San Miguel de Tucumán', 'AR'),
    ('LAP', 'La Plata', 'AR'),
    ('NEU', 'Neuquén', 'AR'),
    ('BBL', 'Bahía Blanca', 'AR'),
    ('RES', 'Resistencia', 'AR'),
    ('SNf', 'Santa Fe', 'AR'),
    ('SAO', 'São Paulo', 'BR'),
    ('RIO', 'Río de Janeiro', 'BR'),
    ('BRA', 'Brasilia', 'BR'),
    ('BEL', 'Belém', 'BR'),
    ('FOR', 'Fortaleza', 'BR'),
    ('SAL', 'Salvador', 'BR'),
    ('MAN', 'Manaus', 'BR'),
    ('CUR', 'Curitiba', 'BR'),
    ('REC', 'Recife', 'BR'),
    ('POA', 'Porto Alegre', 'BR'),

    ('NYC', 'New York City', 'US'),
    ('LA', 'Los Ángeles', 'US'),
    ('CHI', 'Chicago', 'US'),
    ('HOU', 'Houston', 'US'),
    ('PHI', 'Filadelfia', 'US'),
    ('PHO', 'Phoenix', 'US'),
    ('SNN', 'San Antonio', 'US'),
    ('SAN', 'San Diego', 'US'),
    ('DAL', 'Dallas', 'US'),
    ('SNj', 'San José', 'US'),

    ('MAD', 'Madrid', 'ES'),
    ('BCR', 'Barcelona', 'ES'),
    ('VAL', 'Valencia', 'ES'),
    ('SEV', 'Sevilla', 'ES'),
    ('ZAR', 'Zaragoza', 'ES'),
    ('MAL', 'Málaga', 'ES'),
    ('LPA', 'Las Palmas de Gran Canaria', 'ES'),
    ('BIL', 'Bilbao', 'ES'),
    ('MUR', 'Murcia', 'ES'),
    ('ALI', 'Alicante', 'ES'),

    ('MEX', 'Ciudad de México', 'MX'),
    ('GDL', 'Guadalajara', 'MX'),
    ('PUE', 'Puebla', 'MX'),
    ('TIJ', 'Tijuana', 'MX'),
    ('LEO', 'León', 'MX'),
    ('MER', 'Mérida', 'MX'),
    ('MON', 'Monterrey', 'MX'),
    ('ACU', 'Acapulco', 'MX'),
    ('QRO', 'Santiago de Querétaro', 'MX'),
    ('CAN', 'Cancún', 'MX'),
	
    ('BOG', 'Bogotá', 'CO'),
    ('MED', 'Medellín', 'CO'),
    ('CALI', 'Cali', 'CO'),
    ('BAR', 'Barranquilla', 'CO'),
    ('CART', 'Cartagena', 'CO'),
    ('CÚC', 'Cúcuta', 'CO'),
    ('PERE', 'Pereira', 'CO'),
    ('MANI', 'Manizales', 'CO'),
    ('BUC', 'Bucaramanga', 'CO'),
    ('VILL', 'Villavicencio', 'CO');

-- Insertar aeropuertos
INSERT INTO airports (id, name, idCity) VALUES
    ('EZE', 'Aeropuerto Internacional Ministro Pistarini', 'BUE'),
    ('GRU', 'Aeropuerto Internacional de São Paulo-Guarulhos', 'SAO'),
    ('JFK', 'John F. Kennedy International Airport', 'NYC'),
    ('MAD', 'Aeropuerto Adolfo Suárez Madrid-Barajas', 'MAD'),
    ('MEX', 'Aeropuerto Internacional Benito Juárez', 'MEX'),
    ('BOG', 'Aeropuerto Internacional El Dorado', 'BOG'),
    ('GIG', 'Aeropuerto Internacional de Río de Janeiro-Galeão', 'RIO'),
    ('LAX', 'Los Angeles International Airport', 'LA'),
    ('BCN', 'Aeropuerto Josep Tarradellas Barcelona-El Prat', 'BAR'),
    ('CTG', 'Aeropuerto Internacional Rafael Núñez', 'CART');

-- Insertar empleados
INSERT INTO employees (id, name, idRol, ingressDate, idAirline, idAirport) VALUES
    ('E001', 'Pedro Gómez', 1, '2022-01-01', 1, 'EZE'),
    ('E002', 'Laura Fernández', 3, '2022-02-15', 2, 'GRU');

INSERT INTO trips (date, price, originAirport, destinationAirport) VALUES
    ('2024-08-15', 450.00, 'EZE', 'GRU'),
    ('2024-08-20', 380.50, 'BOG', 'MEX'),
    ('2024-09-01', 750.75, 'GRU', 'JFK'),
    ('2024-09-10', 1200.00, 'MEX', 'MAD'),
    ('2024-09-15', 980.25, 'JFK', 'BCN'),
    ('2024-09-22', 650.50, 'MAD', 'BOG'),
    ('2024-10-05', 420.75, 'EZE', 'CTG'),
    ('2024-10-12', 1100.00, 'LAX', 'GIG'),
    ('2024-10-20', 890.50, 'BCN', 'GRU'),
    ('2024-11-01', 1500.25, 'JFK', 'MEX');


-- Insertar conexiones de vuelo
INSERT INTO flightConnections (numConnection, idTrip, idPlane, departureAirport, arrivalAirport, departureTime, arrivalTime, orderNumber, connectionType) VALUES
-- Vuelo 1: EZE - GRU (directo)
('FC001', 1, 1, 'EZE', 'GRU', '2024-08-15 08:00:00', '2024-08-15 11:00:00', 1, 'DIRECT'),

-- Vuelo 2: BOG - MEX (directo)
('FC002', 2, 2, 'BOG', 'MEX', '2024-08-20 09:00:00', '2024-08-20 14:00:00', 1, 'DIRECT'),

-- Vuelo 3: GRU - JFK (con escala en BOG)
('FC003', 3, 3, 'GRU', 'BOG', '2024-09-01 07:00:00', '2024-09-01 11:00:00', 1, 'LAYOVER'),
('FC004', 3, 3, 'BOG', 'JFK', '2024-09-01 13:00:00', '2024-09-01 19:00:00', 2, 'LAYOVER'),

-- Vuelo 4: MEX - MAD (directo)
('FC005', 4, 4, 'MEX', 'MAD', '2024-09-10 23:00:00', '2024-09-11 16:00:00', 1, 'DIRECT'),

-- Vuelo 5: JFK - BCN (con escala en MAD)
('FC006', 5, 5, 'JFK', 'MAD', '2024-09-15 22:00:00', '2024-09-16 11:00:00', 1, 'LAYOVER'),
('FC007', 5, 5, 'MAD', 'BCN', '2024-09-16 13:00:00', '2024-09-16 14:30:00', 2, 'LAYOVER'),

-- Vuelo 6: MAD - BOG (directo)
('FC008', 6, 1, 'MAD', 'BOG', '2024-09-22 10:00:00', '2024-09-22 19:00:00', 1, 'DIRECT'),

-- Vuelo 7: EZE - CTG (con escala en BOG)
('FC009', 7, 2, 'EZE', 'BOG', '2024-10-05 06:00:00', '2024-10-05 11:00:00', 1, 'LAYOVER'),
('FC010', 7, 2, 'BOG', 'CTG', '2024-10-05 13:00:00', '2024-10-05 14:30:00', 2, 'LAYOVER'),

-- Vuelo 8: LAX - GIG (con escala en MEX)
('FC011', 8, 3, 'LAX', 'MEX', '2024-10-12 08:00:00', '2024-10-12 14:00:00', 1, 'LAYOVER'),
('FC012', 8, 3, 'MEX', 'GIG', '2024-10-12 16:00:00', '2024-10-13 06:00:00', 2, 'LAYOVER'),

-- Vuelo 9: BCN - GRU (directo)
('FC013', 9, 4, 'BCN', 'GRU', '2024-10-20 12:00:00', '2024-10-20 20:00:00', 1, 'DIRECT'),

-- Vuelo 10: JFK - MEX (directo)
('FC014', 10, 5, 'JFK', 'MEX', '2024-11-01 09:00:00', '2024-11-01 13:00:00', 1, 'DIRECT');


