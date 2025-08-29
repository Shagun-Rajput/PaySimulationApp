CREATE TABLE dealers (
    dealer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dealer_name VARCHAR(255) NOT NULL,
    dealer_email VARCHAR(255) NOT NULL UNIQUE,
    subscription_type VARCHAR(50) NOT NULL
);

CREATE TABLE vehicles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    model VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    status VARCHAR(50) NOT NULL,
    dealer_id BIGINT NOT NULL,
    CONSTRAINT fk_dealer FOREIGN KEY (dealer_id) REFERENCES dealers(dealer_id) ON DELETE CASCADE
);