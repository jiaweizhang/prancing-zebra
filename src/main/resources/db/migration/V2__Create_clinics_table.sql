CREATE TABLE clinics (
  clinic_id BIGSERIAL,
  name      VARCHAR(255),
  address   VARCHAR(255),
  CONSTRAINT clinics_clinic_id_pkey PRIMARY KEY (clinic_id)
);
