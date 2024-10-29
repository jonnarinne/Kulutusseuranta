DROP TABLE IF EXISTS app_user;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS expense;


CREATE TABLE category (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL
);

INSERT INTO category (name) VALUES 
('Ruoka'),
('Asumiskulut'),
('Harrastukset'),
('Vakuutukset'),
('Matkustaminen'),
('Sisustaminen'),
('Viihde ja vapaa-aika'),
('Lemmikit'),
('Vaatteet ja asusteet');



CREATE TABLE expense (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL CHECK (amount >= 1),
    date DATE,
    shop VARCHAR(30),
    categoryid BIGINT, 
    FOREIGN KEY (categoryid) REFERENCES category(id));


INSERT INTO expense (name, amount, date, shop, categoryid) VALUES 
('Viikon ruokaostokset', 50.0, '2024-10-01', 'Prisma', 1),
('Koiranruoka', 40.0, '2024-10-01', 'Musti ja Mirri', 8),
('Kuntosali', 30.0, '2024-10-02', 'Elixia', 3),
('Elokuvalippu', 15.0, '2024-10-03', 'Finnkino', 7),
('Uusi sohva', 600.0, '2024-10-04', 'IKEA', 6),
('Junalippu Tampereelle', 15.0, '2024-10-05', 'VR', 5),
('Konserttilippu', 49.90, '2024-10-06', 'Ticketmaster', 7),
('Junalippu Helsinkiin', 22.0, '2024-10-07', 'VR', 5),
('Välipalaostoksia', 4.95, '2024-10-08', 'Alepa', 1),
('Käynti uimahallissa', 5.0, '2024-10-09', 'Uimahalli', 7);

CREATE TABLE app_user (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

INSERT INTO app_user (username, password_hash, role) VALUES 
('user', '$2a$10$GCNSYY8Rs9zV0YL0lainN.XBKOlq8/nB4Sf/voyCWKPzgSVupKIiW', 'ROLE_USER'),
('admin', '$2a$10$Oj9Tv75nMXoHQzwxTo1Tseuxf0jwEVkykBFQ6BfI6Ny75GAj0sOjq', 'ROLE_ADMIN');

SELECT * FROM app_user;
SELECT * FROM category;
SELECT * FROM expense;
