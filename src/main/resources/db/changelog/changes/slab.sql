-- liquibase formatted sql
-- changeset ebs:change_0001

CREATE TABLE IF NOT EXISTS slab (
    id BIGSERIAL PRIMARY KEY,
    start_date BIGINT NOT NULL,
    end_date BIGINT NOT NULL,
    unit_from INTEGER NOT NULL,
    unit_to INTEGER NOT NULL,
    tariff DOUBLE PRECISION NOT NULL,
    type VARCHAR(255) NOT NULL,
    created_at BIGINT NOT NULL,
    updated_at BIGINT
    );
