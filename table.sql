-- public.contact definition

-- Drop table

-- DROP TABLE public.contact;

CREATE TABLE public.contact (
	id bigserial NOT NULL,
	description varchar(255) NOT NULL,
	email varchar(70) NOT NULL,
	"name" varchar(100) NOT NULL,
	phone varchar(20) NOT NULL,
	secondname varchar(100) NOT NULL,
	"work" varchar(100) NOT NULL,
	created_at timestamp NOT NULL,
	updated_at timestamp NULL,
	is_active bool NULL,
	CONSTRAINT contact_pkey PRIMARY KEY (id)
);
ALTER TABLE contact ADD CONSTRAINT unique_email UNIQUE (email);
ALTER TABLE contact ADD CONSTRAINT unique_phone UNIQUE (phone);
