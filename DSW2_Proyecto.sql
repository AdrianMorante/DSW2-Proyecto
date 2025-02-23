
DROP DATABASE IF EXISTS dsw2_proyecto;

CREATE DATABASE dsw2_proyecto;
USE dsw2_proyecto;

CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE alumnos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    edad INT NOT NULL,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);
        
INSERT INTO alumnos (nombre, apellido, edad, fecha_creacion) VALUES 
('Carlos', 'Hernández', 25, '2024-01-15 10:30:00'),
('María', 'Lopez', 22, '2024-02-20 14:45:00'),
('Juan', 'Martínez', 28, '2024-03-10 09:15:00'),
('Ana', 'González', 24, '2024-04-05 11:50:00'),
('Luis', 'Ramírez', 27, '2024-05-25 13:20:00'),
('Sofía', 'Fernández', 21, '2024-06-30 08:10:00'),
('Miguel', 'Castro', 26, '2024-07-22 15:30:00'),
('Lucía', 'Vargas', 23, '2024-08-18 17:00:00'),
('Javier', 'Pérez', 29, '2024-09-09 12:45:00'),
('Camila', 'Mendoza', 20, '2024-10-14 16:25:00');



select * from usuario;
-- select * from alumnos;
