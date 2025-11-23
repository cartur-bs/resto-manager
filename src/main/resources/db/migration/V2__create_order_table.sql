CREATE TABLE order_table (
    order_id UUID PRIMARY KEY,
    customer_name VARCHAR(255),
    table_number INTEGER,
    order_date TIMESTAMP,
    is_takeout BOOLEAN,
    order_status VARCHAR(50),
    order_total NUMERIC(38,2)
);
