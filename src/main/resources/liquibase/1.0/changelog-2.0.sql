CREATE TABLE Stocks
(
    stock_id     SERIAL PRIMARY KEY,
    symbol       VARCHAR(255),
    company_name VARCHAR(255),
    price        NUMERIC
);