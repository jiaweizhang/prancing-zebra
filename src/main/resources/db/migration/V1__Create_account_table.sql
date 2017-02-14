CREATE TABLE account (
  account_id   BIGSERIAL PRIMARY KEY,
  first_name   VARCHAR(255) NOT NULL,
  last_name    VARCHAR(255) NOT NULL,
  phone_number VARCHAR(255),
  email        VARCHAR(255),
  passhash     VARCHAR(255) NOT NULL
);

-- add constraint that requires either phone_number or email to exist