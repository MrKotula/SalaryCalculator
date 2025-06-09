CREATE TABLE IF NOT EXISTS night_shift_rate
(
    id          SERIAL PRIMARY KEY,
    month       VARCHAR(20)    NOT NULL,
    year        INT            NOT NULL,
    brutto_rate DECIMAL(10, 2) NOT NULL
);