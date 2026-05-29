CREATE TABLE products.products (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(256) NOT NULL,
    price REAL NOT NULL,
    unit VARCHAR(256) NOT NULL
);