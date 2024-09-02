CREATE TABLE IF NOT EXISTS app_user
(
	id SERIAL PRIMARY KEY,
	username VARCHAR (255) UNIQUE NOT NULL,
	password VARCHAR (64) NOT NULL,
	role VARCHAR (16) NOT NULL,
	creation_date TIMESTAMP NOT NULL,
	editing_date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS product
(
	id SERIAL PRIMARY KEY,
	author_id INT REFERENCES app_user (id) NOT NULL,
	name VARCHAR (255) UNIQUE NOT NULL,
	description VARCHAR (1024) UNIQUE NOT NULL,
	creation_date TIMESTAMP NOT NULL,
	editing_date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS tag
(
	id SERIAL PRIMARY KEY,
	author_id INT REFERENCES app_user (id) NOT NULL,
	name VARCHAR (255) UNIQUE NOT NULL,
	creation_date TIMESTAMP NOT NULL,
	editing_date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS comment
(
	id SERIAL PRIMARY KEY,
	product_id INT REFERENCES product (id) NOT NULL,
	author_id INT REFERENCES app_user (id) NOT NULL,
	text VARCHAR (1024) NOT NULL,
	creation_date TIMESTAMP NOT NULL,
	editing_date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS product_tag
(
	id SERIAL PRIMARY KEY,
	product_id INT REFERENCES product (id) NOT NULL,
	tag_id INT REFERENCES tag (id) NOT NULL,

	CONSTRAINT product_tag__many_to_many_unique UNIQUE (product_id, tag_id)
);
