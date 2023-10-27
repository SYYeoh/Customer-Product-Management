CREATE SCHEMA IF NOT EXISTS customer_product;

CREATE SEQUENCE IF NOT EXISTS SEQ_CUSTOMER
START WITH 1
INCREMENT BY 1
CYCLE
CACHE 100;

CREATE TABLE IF NOT EXISTS customer_product.customers (
    cust_id INT NOT NULL DEFAULT NEXT VALUE FOR SEQ_CUSTOMER,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email_office VARCHAR(255),
    email_personal VARCHAR(255),
    address JSON NOT NULL,
    PRIMARY KEY (cust_id)
);

INSERT INTO customer_product.customers (cust_id, first_name, last_name, email_office, email_personal, address)
VALUES ('1','John', 'Doe', 'john.doe@company.com', 'john.doe@gmail.com', '{"street": "123 Main St", "city": "New York", "zip": "10001"}');
INSERT INTO customer_product.customers (cust_id, first_name, last_name, email_office, email_personal, address)
VALUES ('2','Alice', 'Smith', 'alice.smith@company.com', 'alice.smith@gmail.com', '{"street": "456 Elm St", "city": "Los Angeles", "zip": "90001"}');
INSERT INTO customer_product.customers (cust_id, first_name, last_name, email_office, email_personal, address)
VALUES ('3','Bob', 'Johnson', 'bob.johnson@company.com', 'bob.johnson@gmail.com', '{"street": "789 Oak St", "city": "Chicago", "zip": "60601"}');
INSERT INTO customer_product.customers (cust_id, first_name, last_name, email_office, email_personal, address)
VALUES ('4','Sarah', 'Williams', 'sarah.williams@company.com', 'sarah.williams@gmail.com', '{"street": "101 Pine St", "city": "San Francisco", "zip": "94101"}');
INSERT INTO customer_product.customers (cust_id, first_name, last_name, email_office, email_personal, address)
VALUES ('5','Michael', 'Brown', 'michael.brown@company.com', 'michael.brown@gmail.com', '{"street": "222 Cedar St", "city": "Houston", "zip": "77001"}');

CREATE SEQUENCE IF NOT EXISTS SEQ_PRODUCT
START WITH 1
INCREMENT BY 1
CYCLE
CACHE 100;

CREATE TABLE IF NOT EXISTS customer_product.products (
    pid INT NOT NULL DEFAULT NEXT VALUE FOR SEQ_PRODUCT,
    book_title VARCHAR(255) NOT NULL,
    book_genre VARCHAR(100) NOT NULL,
    book_price DECIMAL(10,2) NOT NULL,
    book_quantity INT NOT NULL,
    PRIMARY KEY (pid)
);

INSERT INTO customer_product.products (pid, book_title, book_genre, book_price, book_quantity)
VALUES ('1', 'The Great Gatsby', 'Fiction', 19.99, 50);
INSERT INTO customer_product.products (pid, book_title, book_genre, book_price, book_quantity)
VALUES ('2', 'To Kill a Mockingbird', 'Fiction', 14.99, 75);
INSERT INTO customer_product.products (pid, book_title, book_genre, book_price, book_quantity)
VALUES ('3', '1984', 'Science Fiction', 22.50, 60);
INSERT INTO customer_product.products (pid, book_title, book_genre, book_price, book_quantity)
VALUES ('4', 'Pride and Prejudice', 'Romance', 17.99, 45);
INSERT INTO customer_product.products (pid, book_title, book_genre, book_price, book_quantity)
VALUES ('5', 'The Catcher in the Rye', 'Fiction', 16.50, 55);