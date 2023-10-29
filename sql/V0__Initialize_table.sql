CREATE SCHEMA IF NOT EXISTS customer_product;

USE customer_product;

CREATE SEQUENCE IF NOT EXISTS customer_product.SEQ_CUSTOMER
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
    address_line VARCHAR(255),
    city VARCHAR(255),
    postal VARCHAR(255),
    status VARCHAR(100),
    PRIMARY KEY (cust_id)
);

INSERT INTO customer_product.customers (cust_id, first_name, last_name, email_office, email_personal, address_line, city, postal, status)
VALUES ('1','John', 'Doe', 'john.doe@company.com', 'john.doe@gmail.com', '123 Main St', 'New York', '10001', 'ACTIVE');
INSERT INTO customer_product.customers (cust_id, first_name, last_name, email_office, email_personal, address_line, city, postal, status)
VALUES ('2','Alice', 'Smith', 'alice.smith@company.com', 'alice.smith@gmail.com', '456 Elm St', 'Los Angeles', '90001', 'ACTIVE');
INSERT INTO customer_product.customers (cust_id, first_name, last_name, email_office, email_personal, address_line, city, postal, status)
VALUES ('3','Bob', 'Johnson', 'bob.johnson@company.com', 'bob.johnson@gmail.com', '789 Oak St', 'Chicago', '60601', 'ACTIVE');
INSERT INTO customer_product.customers (cust_id, first_name, last_name, email_office, email_personal, address_line, city, postal, status)
VALUES ('4','Sarah', 'Williams', 'sarah.williams@company.com', 'sarah.williams@gmail.com', '101 Pine St', 'San Francisco', '94101', 'ACTIVE');
INSERT INTO customer_product.customers (cust_id, first_name, last_name, email_office, email_personal, address_line, city, postal, status)
VALUES ('5','Michael', 'Brown', 'michael.brown@company.com', 'michael.brown@gmail.com', '222 Cedar St', 'Houston', '77001', 'ACTIVE');

# Update Customer SEQ
ALTER SEQUENCE SEQ_CUSTOMER RESTART WITH 6;

CREATE SEQUENCE IF NOT EXISTS customer_product.SEQ_PRODUCT
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
    status VARCHAR(255),
    PRIMARY KEY (pid)
);

INSERT INTO customer_product.products (pid, book_title, book_genre, book_price, book_quantity, status)
VALUES ('1', 'The Great Gatsby', 'Fiction', 19.99, 50, 'ACTIVE');
INSERT INTO customer_product.products (pid, book_title, book_genre, book_price, book_quantity, status)
VALUES ('2', 'To Kill a Mockingbird', 'Fiction', 14.99, 75, 'ACTIVE');
INSERT INTO customer_product.products (pid, book_title, book_genre, book_price, book_quantity, status)
VALUES ('3', '1984', 'Science Fiction', 22.50, 60, 'ACTIVE');
INSERT INTO customer_product.products (pid, book_title, book_genre, book_price, book_quantity, status)
VALUES ('4', 'Pride and Prejudice', 'Romance', 17.99, 45, 'ACTIVE');
INSERT INTO customer_product.products (pid, book_title, book_genre, book_price, book_quantity, status)
VALUES ('5', 'The Catcher in the Rye', 'Fiction', 16.50, 55, 'DISCONTINUED');

# update product seq
ALTER SEQUENCE SEQ_PRODUCT RESTART WITH 6;

# Product history entity
CREATE SEQUENCE IF NOT EXISTS customer_product.SEQ_PRODUCT_HISTORY
START WITH 1
INCREMENT BY 1
CYCLE
CACHE 100;

CREATE TABLE IF NOT EXISTS customer_product.products_history (
    product_history_id INT NOT NULL DEFAULT NEXT VALUE FOR SEQ_PRODUCT_HISTORY,
    pid INT NOT NULL,
    book_title VARCHAR(255) NOT NULL,
    book_genre VARCHAR(100) NOT NULL,
    book_price DECIMAL(10,2) NOT NULL,
    book_quantity INT NOT NULL,
    prod_status VARCHAR(100),
    updated_time TIMESTAMP,
    PRIMARY KEY (product_history_id),
    FOREIGN KEY (pid) REFERENCES products(pid)
);

# Order entity
CREATE SEQUENCE IF NOT EXISTS customer_product.SEQ_ORDER
START WITH 1
INCREMENT BY 1
CYCLE
CACHE 100;

CREATE TABLE IF NOT EXISTS customer_product.orders (
    order_id INT NOT NULL DEFAULT NEXT VALUE FOR SEQ_ORDER,
    cust_id INT NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email_office VARCHAR(255),
    email_personal VARCHAR(255),
    address_line VARCHAR(255),
    city VARCHAR(255),
    postal VARCHAR(255),
    sku INT NOT NULL,
    created_time TIMESTAMP,
    updated_time TIMESTAMP,
    status VARCHAR(100),
    PRIMARY KEY (order_id),
    FOREIGN KEY (cust_id) REFERENCES customers(cust_id)
);

# Order Item
CREATE SEQUENCE IF NOT EXISTS customer_product.SEQ_ORDER_ITEM
START WITH 1
INCREMENT BY 1
CYCLE
CACHE 100;

CREATE TABLE IF NOT EXISTS customer_product.order_item (
    sku INT NOT NULL DEFAULT NEXT VALUE FOR SEQ_ORDER_ITEM,
    order_id INT NOT NULL,
    pid INT NOT NULL,
    book_title VARCHAR(255) NOT NULL,
    book_genre VARCHAR(100) NOT NULL,
    book_price DECIMAL(10,2) NOT NULL,
    quantity INT NOT NULL,
    PRIMARY KEY (sku),
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (pid) REFERENCES products(pid)
);

# Order hist entity
CREATE SEQUENCE IF NOT EXISTS SEQ_ORDER_HISTORY
START WITH 1
INCREMENT BY 1
CYCLE
CACHE 100;

CREATE TABLE IF NOT EXISTS customer_product.order_history (
    order_hist_id INT NOT NULL DEFAULT NEXT VALUE FOR SEQ_ORDER_HISTORY,
    order_id INT NOT NULL,
    cust_id INT NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email_office VARCHAR(255),
    email_personal VARCHAR(255),
    address_line VARCHAR(255),
    city VARCHAR(255),
    postal VARCHAR(255),
    sku INT NOT NULL,
    quantity INT NOT NULL,
    order_status VARCHAR(100),
    created_time TIMESTAMP,
    updated_time TIMESTAMP,
    PRIMARY KEY (order_hist_id),
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (cust_id) REFERENCES customers(cust_id),
    FOREIGN KEY (sku) REFERENCES order_item(sku)
);
