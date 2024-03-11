-- V1__create_table_staff_hierarchy_table.sql

CREATE TABLE IF NOT EXISTS staff_hierarchy (
        id BIGINT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        password VARCHAR(255) NOT NULL,
        password_rate VARCHAR(255),
        score_password DECIMAL(10,2),
        hierarchy VARCHAR(255) NOT NULL,
        order_hierarchy BIGINT NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    );
