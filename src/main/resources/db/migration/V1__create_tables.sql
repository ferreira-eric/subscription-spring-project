CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       full_name VARCHAR(255) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE status (
                        id SERIAL PRIMARY KEY,
                        status_name VARCHAR(50) NOT NULL
);

CREATE TABLE subscription (
                              id SERIAL PRIMARY KEY,
                              users_id INT NOT NULL UNIQUE,
                              status_id INT NOT NULL,
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                              updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                              FOREIGN KEY (users_id) REFERENCES users(id) ON DELETE RESTRICT ON UPDATE CASCADE,
                              FOREIGN KEY (status_id) REFERENCES status(id) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE event_history (
                               id SERIAL PRIMARY KEY,
                               subscription_id INT NOT NULL,
                               type VARCHAR(100) NOT NULL,
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                               FOREIGN KEY (subscription_id) REFERENCES subscription(id) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE payment (
                         id SERIAL PRIMARY KEY,
                         subscription_id INT NOT NULL,
                         users_id INT NOT NULL UNIQUE,
                         amount DECIMAL(10,2) NOT NULL,
                         status VARCHAR(50) NOT NULL,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                         FOREIGN KEY (subscription_id) REFERENCES subscription(id) ON DELETE RESTRICT ON UPDATE CASCADE,
                         FOREIGN KEY (users_id) REFERENCES users(id) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE transaction (
                         id UUID PRIMARY KEY,
                         payment_id INT NOT NULL UNIQUE,
                         FOREIGN KEY (payment_id) REFERENCES payment(id) ON DELETE RESTRICT ON UPDATE CASCADE

);
