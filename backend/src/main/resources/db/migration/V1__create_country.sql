CREATE TABLE country(
  id BIGSERIAL PRIMARY KEY,
  country VARCHAR(255) NOT NULL UNIQUE,
  code VARCHAR(255),
  capital VARCHAR(255),
  region VARCHAR(255),
  population VARCHAR(255),
  currency VARCHAR(255)
);
