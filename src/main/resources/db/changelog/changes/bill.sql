-- liquibase formatted sql
-- changeset ebs:change_0001
CREATE TABLE IF NOT EXISTS bill (
    id BIGSERIAL PRIMARY KEY,
    bill_number BIGINT NOT NULL UNIQUE,
    consumer_id BIGINT NOT NULL,
    consumed_units DOUBLE PRECISION NOT NULL,
    due_amount DOUBLE PRECISION NOT NULL,
    paid SMALLINT NOT NULL,
    due_date DATE NULL,
    created_at BIGINT NOT NULL,
    updated_at BIGINT,
    CONSTRAINT fk_bill_consumer FOREIGN KEY (consumer_id) REFERENCES consumer (id)

);