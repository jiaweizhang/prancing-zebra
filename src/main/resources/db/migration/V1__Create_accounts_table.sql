CREATE TABLE accounts (
  account_id                   BIGSERIAL,
  name                         VARCHAR(255),
  phone_number                 VARCHAR(255) NOT NULL,
  passhash                     VARCHAR(255),
  verification_code            VARCHAR(255),
  verification_code_expiration TIMESTAMP,
  registered                   BOOLEAN,
  CONSTRAINT accounts_account_id_pkey PRIMARY KEY (account_id),
  CONSTRAINT accounts_phone_number_key UNIQUE (phone_number)
);
