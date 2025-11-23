CREATE TABLE prod_order_table (
    product_order_id UUID PRIMARY KEY,
    order_id UUID,
    prod_id UUID,
    prod_unit_price NUMERIC(38,2),
    prod_order_quantity INTEGER,

    CONSTRAINT fk_prod_order_order
        FOREIGN KEY (order_id)
        REFERENCES order_table(order_id)
        ON DELETE CASCADE,

    CONSTRAINT fk_prod_order_product
        FOREIGN KEY (prod_id)
        REFERENCES prod_model_table(prod_id)
);
