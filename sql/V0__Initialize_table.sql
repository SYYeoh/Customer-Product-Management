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
VALUES ('5','Michael', 'Brown', 'michael.brown@company.com', 'michael.brown@gmail.com', '222 Cedar St', 'Houston', '77001', 'INACTIVE');
INSERT INTO customer_product.customers (cust_id, first_name, last_name, email_office, email_personal, address_line, city, postal, status)
VALUES
    ('6', 'Customer1', 'LastName1', 'customer1@company.com', 'customer1@gmail.com', '123 Main St', 'New York', '10001', 'ACTIVE'),
    ('7', 'Customer2', 'LastName2', 'customer2@company.com', 'customer2@gmail.com', '124 Main St', 'New York', '10002', 'ACTIVE'),
    ('8', 'Customer3', 'LastName3', 'customer3@company.com', 'customer3@gmail.com', '125 Main St', 'New York', '10003', 'ACTIVE'),
    ('9', 'Customer4', 'LastName4', 'customer4@company.com', 'customer4@gmail.com', '126 Main St', 'New York', '10004', 'ACTIVE'),
    ('10', 'Customer5', 'LastName5', 'customer5@company.com', 'customer5@gmail.com', '127 Main St', 'New York', '10005', 'ACTIVE'),
    ('11', 'Customer6', 'LastName6', 'customer6@company.com', 'customer6@gmail.com', '128 Main St', 'New York', '10006', 'ACTIVE'),
    ('12', 'Customer7', 'LastName7', 'customer7@company.com', 'customer7@gmail.com', '129 Main St', 'New York', '10007', 'ACTIVE'),
    ('13', 'Customer8', 'LastName8', 'customer8@company.com', 'customer8@gmail.com', '130 Main St', 'New York', '10008', 'ACTIVE'),
    ('14', 'Customer9', 'LastName9', 'customer9@company.com', 'customer9@gmail.com', '131 Main St', 'New York', '10009', 'ACTIVE'),
    ('15', 'Customer10', 'LastName10', 'customer10@company.com', 'customer10@gmail.com', '132 Main St', 'New York', '10010', 'ACTIVE');


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
    deleted boolean,
    PRIMARY KEY (pid)
);

INSERT INTO customer_product.products (pid, book_title, book_genre, book_price, book_quantity, status, deleted)
VALUES ('1', 'The Great Gatsby', 'Fiction', 19.99, 50, 'ACTIVE', 0);
INSERT INTO customer_product.products (pid, book_title, book_genre, book_price, book_quantity, status, deleted)
VALUES ('2', 'To Kill a Mockingbird', 'Fiction', 14.99, 75, 'ACTIVE', 0);
INSERT INTO customer_product.products (pid, book_title, book_genre, book_price, book_quantity, status, deleted)
VALUES ('3', '1984', 'Science Fiction', 22.50, 60, 'ACTIVE', 0);
INSERT INTO customer_product.products (pid, book_title, book_genre, book_price, book_quantity, status, deleted)
VALUES ('4', 'Pride and Prejudice', 'Romance', 17.99, 45, 'ACTIVE', 0);
INSERT INTO customer_product.products (pid, book_title, book_genre, book_price, book_quantity, status, deleted)
VALUES ('5', 'The Catcher in the Rye', 'Fiction', 16.50, 55, 'DISCONTINUED', 0);

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


INSERT INTO orders (address_line, city, created_time, cust_id, email_office, email_personal, first_name, last_name, order_id, postal, sku, status, updated_time)
VALUES ('123 Main St', 'New York', '2023-10-31 21:40:43', 1, 'john.doe@company.com', 'john.doe@gmail.com', 'John', 'Doe', 1, '10001', '111', 'PENDING', '2023-10-31 21:40:43');
INSERT INTO orders (address_line, city, created_time, cust_id, email_office, email_personal, first_name, last_name, order_id, postal, sku, status, updated_time)
VALUES ('123 Main St', 'New York', '2023-10-31 21:40:43', 1, 'john.doe@company.com', 'john.doe@gmail.com', 'John', 'Doe', 2, '10001', '222', 'PENDING', '2023-10-31 22:40:43');
INSERT INTO orders (address_line, city, created_time, cust_id, email_office, email_personal, first_name, last_name, order_id, postal, sku, status, updated_time)
VALUES ('789 Oak St', 'Chicago', '2023-10-31 21:40:43', 3, 'bob.johnson@company.com', 'bob.johnson@gmail.com', 'Bob', 'Johnson', 3, '60601', '333', 'PENDING', '2023-10-31 21:40:43');
INSERT INTO orders (address_line, city, created_time, cust_id, email_office, email_personal, first_name, last_name, order_id, postal, sku, status, updated_time)
VALUES ('101 Pine St', 'San Francisco', '2023-10-31 21:40:43', 4, 'sarah.williams@company.com', 'sarah.williams@gmail.com', 'Sarah', 'Williams', 4, '94101', '444', 'PENDING', '2023-10-31 21:40:43');
INSERT INTO orders (address_line, city, created_time, cust_id, email_office, email_personal, first_name, last_name, order_id, postal, sku, status, updated_time)
VALUES ('222 Cedar St', 'Houston', '2023-10-31 21:40:43', 5, 'michael.brown@company.com', 'michael.brown@gmail.com', 'Michael', 'Brown', 5, '77001', '555', 'PENDING', '2023-10-31 21:40:43');

INSERT INTO customer_product.order_item (sku, order_id, pid, book_title, book_genre, book_price, quantity)
VALUES ('111', 1, 1, 'The Great Gatsby', 'Fiction', 19.99, 1);
INSERT INTO customer_product.order_item (sku, order_id, pid, book_title, book_genre, book_price, quantity)
VALUES ('222',2, 2, 'To Kill a Mockingbird', 'Fiction', 14.99, 1);
INSERT INTO customer_product.order_item (sku, order_id, pid, book_title, book_genre, book_price, quantity)
VALUES ('333',3, 3, '1984', 'Science Fiction', 22.50, 1);
INSERT INTO customer_product.order_item (sku, order_id, pid, book_title, book_genre, book_price, quantity)
VALUES ('444',4, 4, 'Pride and Prejudice', 'Romance', 17.99, 1);
INSERT INTO customer_product.order_item (sku, order_id, pid, book_title, book_genre, book_price, quantity)
VALUES ('555',5, 5, 'The Catcher in the Rye', 'Fiction', 16.50, 1);