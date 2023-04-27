CREATE SEQUENCE hibernate_sequence START 1;

CREATE TABLE IF NOT EXISTS "item" (
    id int NOT NULL PRIMARY KEY,
    category varchar(25) NOT NULL DEFAULT 'food',
    name varchar(25) NOT NULL DEFAULT 'meal',
    brand varchar(25) NOT NULL DEFAULT 'house',
    price float
);

CREATE TABLE IF NOT EXISTS "user" (
    id int NOT NULL PRIMARY KEY,
    code varchar(7) NOT NULL DEFAULT 'ba01234',
    name varchar(30) NOT NULL DEFAULT 'Ana Ban'
);

CREATE TABLE IF NOT EXISTS "order" (
    id int NOT NULL PRIMARY KEY,
    price float,
    fk_user_id int,
    FOREIGN KEY (fk_user_id) REFERENCES "user"(id)
);

CREATE TABLE IF NOT EXISTS "order_item" (
    itemId int NOT NULL,
    orderId int NOT NULL,

    FOREIGN KEY (itemId) REFERENCES item(id),
    FOREIGN KEY (orderId) REFERENCES "order"(id),

    UNIQUE(itemId, orderId)
);


