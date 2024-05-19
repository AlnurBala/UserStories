CREATE TABLE CashBalance
(
    user_id INTEGER PRIMARY KEY,
    balance NUMERIC,
    FOREIGN KEY (user_id) REFERENCES Users (user_id)
);