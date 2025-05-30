CREATE TABLE night_shift_rate (
                                  id SERIAL PRIMARY KEY,
                                  month VARCHAR(20) NOT NULL,
                                  year INT NOT NULL,
                                  brutto_rate DECIMAL(10,2) NOT NULL
);

INSERT INTO night_shift_rate (month, year, brutto_rate) VALUES ('JANUARY', 2024, 5.05);
INSERT INTO night_shift_rate (month, year, brutto_rate) VALUES ('FEBRUARY', 2024, 5.05);
INSERT INTO night_shift_rate (month, year, brutto_rate) VALUES ('MARCH', 2024, 5.05);
INSERT INTO night_shift_rate (month, year, brutto_rate) VALUES ('APRIL', 2024, 5.05);
INSERT INTO night_shift_rate (month, year, brutto_rate) VALUES ('MAY', 2024, 5.30);
INSERT INTO night_shift_rate (month, year, brutto_rate) VALUES ('JUNE', 2024, 5.30);
INSERT INTO night_shift_rate (month, year, brutto_rate) VALUES ('JULY', 2024, 4.67);
INSERT INTO night_shift_rate (month, year, brutto_rate) VALUES ('AUGUST', 2024, 5.12);
INSERT INTO night_shift_rate (month, year, brutto_rate) VALUES ('SEPTEMBER', 2024, 5.12);
INSERT INTO night_shift_rate (month, year, brutto_rate) VALUES ('OCTOBER', 2024, 4.67);
INSERT INTO night_shift_rate (month, year, brutto_rate) VALUES ('NOVEMBER', 2024, 5.66);
INSERT INTO night_shift_rate (month, year, brutto_rate) VALUES ('DECEMBER', 2024, 5.38);

INSERT INTO night_shift_rate (month, year, brutto_rate) VALUES ('JANUARY', 2025, 5.55);
INSERT INTO night_shift_rate (month, year, brutto_rate) VALUES ('FEBRUARY', 2025, 5.83);
INSERT INTO night_shift_rate (month, year, brutto_rate) VALUES ('MARCH', 2025, 5.55);
INSERT INTO night_shift_rate (month, year, brutto_rate) VALUES ('APRIL', 2025, 5.55);
INSERT INTO night_shift_rate (month, year, brutto_rate) VALUES ('MAY', 2025, 5.83);
INSERT INTO night_shift_rate (month, year, brutto_rate) VALUES ('JUNE', 2025, 5.83);
INSERT INTO night_shift_rate (month, year, brutto_rate) VALUES ('JULY', 2025, 5.07);
INSERT INTO night_shift_rate (month, year, brutto_rate) VALUES ('AUGUST', 2025, 5.83);
INSERT INTO night_shift_rate (month, year, brutto_rate) VALUES ('SEPTEMBER', 2025, 5.3);
INSERT INTO night_shift_rate (month, year, brutto_rate) VALUES ('OCTOBER', 2025, 5.07);
INSERT INTO night_shift_rate (month, year, brutto_rate) VALUES ('NOVEMBER', 2025, 6.48);
INSERT INTO night_shift_rate (month, year, brutto_rate) VALUES ('DECEMBER', 2025, 5.55);
