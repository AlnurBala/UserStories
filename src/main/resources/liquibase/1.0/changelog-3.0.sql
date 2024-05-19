CREATE TABLE Orders
(
    order_id         SERIAL PRIMARY KEY,
    user_id          INTEGER,
    stock_id         INTEGER,
    order_type       VARCHAR(255),
    target_price     NUMERIC,
    filled_timestamp TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users (user_id),
    FOREIGN KEY (stock_id) REFERENCES Stocks (stock_id)
);