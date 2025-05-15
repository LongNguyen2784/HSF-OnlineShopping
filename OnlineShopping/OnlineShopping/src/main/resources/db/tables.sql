-- Create Database
CREATE DATABASE northwind;


-- Northwind Database - PostgreSQL Schema
use northwind;
-- Categories Table
CREATE TABLE categories (
                            category_id INT PRIMARY KEY,
                            category_name TEXT NOT NULL,
                            description TEXT
);

-- Suppliers Table
CREATE TABLE suppliers (
                           supplier_id INT PRIMARY KEY,
                           company_name TEXT NOT NULL,
                           contact_name TEXT,
                           contact_title TEXT,
                           address TEXT,
                           city TEXT,
                           region TEXT,
                           postal_code TEXT,
                           country TEXT,
                           phone TEXT,
                           fax TEXT,
                           home_page TEXT
);

-- Products Table
CREATE TABLE products (
                          product_id INT PRIMARY KEY,
                          product_name TEXT NOT NULL,
                          supplier_id INT,
                          category_id INT,
                          quantity_per_unit TEXT,
                          unit_price DECIMAL(18, 2),
--                        units_in_stock INT,
                          units_on_order INT,
                          reorder_level INT,
                          discontinued INT,
                          FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id),
                          FOREIGN KEY (category_id) REFERENCES categories(category_id)
);

-- Customers Table
CREATE TABLE customers (
                           customer_id INT PRIMARY KEY,
                           company_name TEXT NOT NULL,
                           contact_name TEXT,
                           contact_title TEXT,
                           address TEXT,
                           city TEXT,
                           region TEXT,
                           postal_code TEXT,
                           country TEXT,
                           phone TEXT,
                           fax TEXT
);

-- Employees Table
CREATE TABLE employees (
                           employee_id INT PRIMARY KEY,
                           last_name TEXT NOT NULL,
                           first_name TEXT NOT NULL,
                           title TEXT,
                           title_of_courtesy TEXT,
                           birth_date DATE,
                           hire_date DATE,
                           address TEXT,
                           city TEXT,
                           region TEXT,
                           postal_code TEXT,
                           country TEXT,
                           home_phone TEXT,
                           extension TEXT,
                           reports_to INT,
                           FOREIGN KEY (reports_to) REFERENCES employees(employee_id)
);

-- Shippers Table
CREATE TABLE shippers (
                          shipper_id INT PRIMARY KEY,
                          company_name TEXT NOT NULL,
                          phone TEXT
);

-- Orders Table
CREATE TABLE orders (
                        order_id INT PRIMARY KEY,
                        customer_id INT,
                        employee_id INT,
                        order_date DATE,
                        required_date DATE,
                        shipped_date DATE,
                        ship_via INT,
                        freight DECIMAL(18, 2),
                        ship_name TEXT,
                        ship_address TEXT,
                        ship_city TEXT,
                        ship_region TEXT,
                        ship_postal_code TEXT,
                        ship_country TEXT,
                        FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
                        FOREIGN KEY (employee_id) REFERENCES employees(employee_id),
                        FOREIGN KEY (ship_via) REFERENCES shippers(shipper_id)
);

-- Order Details Table
CREATE TABLE order_details (
                               order_id INT,
                               product_id INT,
                               quantity INT NOT NULL,
                               discount DECIMAL(18, 2),
                               PRIMARY KEY (order_id, product_id),
                               FOREIGN KEY (order_id) REFERENCES orders(order_id),
                               FOREIGN KEY (product_id) REFERENCES products(product_id)
);

-- Regions Table
CREATE TABLE regions (
                         region_id INT PRIMARY KEY,
                         region_description TEXT NOT NULL
);

-- Territories Table
CREATE TABLE territories (
                             territory_id INT PRIMARY KEY,
                             territory_description TEXT NOT NULL,
                             region_id INT NOT NULL,
                             FOREIGN KEY (region_id) REFERENCES regions(region_id)
);

-- Employee Territories Table
CREATE TABLE employee_territories (
                                      employee_id INT NOT NULL,
                                      territory_id INT NOT NULL,
                                      PRIMARY KEY (employee_id, territory_id),
                                      FOREIGN KEY (employee_id) REFERENCES employees(employee_id),
                                      FOREIGN KEY (territory_id) REFERENCES territories(territory_id)
);
