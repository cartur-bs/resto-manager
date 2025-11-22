CREATE TABLE prod_model_table (
    prod_id UUID PRIMARY KEY,
    is_prod_available BOOLEAN,
    prod_category VARCHAR(255),
    prod_description VARCHAR(255),
    prod_name VARCHAR(50),
    prod_price NUMERIC(38,2)
);
