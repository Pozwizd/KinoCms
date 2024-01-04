create schema kino_cms;

use kino_cms;
INSERT INTO User (name, surname, nickname, email, address, password, card_number, language, gender, phone_number, date_of_birth, city)
VALUES ('John', 'Doe', 'johndoe', 'johndoe@example.com', '123 Main St', 'password123', '1234567890123456', 'Русский', 'MALE', '1234567890', '1990-01-01', 'New York'),
       ('Jane', 'Smith', 'janesmith', 'janesmith@example.com', '456 Elm St', 'password456', '9876543210987654', 'Русский', 'FEMALE', '9876543210', '1995-02-15', 'Paris');
