-- liquibase formatted sql
-- changeset ebs:change_0001

CREATE TABLE IF NOT EXISTS consumer (
    id BIGSERIAL PRIMARY KEY,
    consumer_number BIGINT UNIQUE NOT NULL,
    slab_id BIGINT NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255),
    mobile_number VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255),
    address_line1 VARCHAR(255),
    address_line2 VARCHAR(255),
    zip VARCHAR(255),
    district VARCHAR(255),
    state VARCHAR(255),
    created_at BIGINT NOT NULL,
    updated_at BIGINT,
    CONSTRAINT fk_bill_slab FOREIGN KEY (slab_id) REFERENCES slab (id)
    );
