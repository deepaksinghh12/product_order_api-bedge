-- Insert initial data
INSERT INTO products (id, name, category, price) VALUES (1, 'Wireless Mouse', 'Electronics', 399.0);
INSERT INTO products (id, name, category, price) VALUES (2, 'Notebook', 'Stationery', 49.0);

INSERT INTO customers (id, name, email, region) VALUES (1, 'Deepak Singh', 'deepak@example.com', 'Rajasthan');

-- IMPORTANT: This updates the database's internal ID sequence counter.
-- It tells the sequence to start counting from the next available number after the highest ID we inserted above.
ALTER TABLE products ALTER COLUMN id RESTART WITH (SELECT MAX(id) FROM products) + 1;
ALTER TABLE customers ALTER COLUMN id RESTART WITH (SELECT MAX(id) FROM customers) + 1;

