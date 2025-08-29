
CREATE TABLE dealers (
    dealer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dealer_name VARCHAR(255) NOT NULL,
    dealer_email VARCHAR(255) NOT NULL UNIQUE,
    subscription_type VARCHAR(50) NOT NULL
);

