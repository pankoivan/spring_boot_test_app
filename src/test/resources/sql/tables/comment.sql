CREATE TABLE IF NOT EXISTS comment
(
	id SERIAL PRIMARY KEY,
	product_id INT REFERENCES product (id),
	author_id INT REFERENCES app_user (id),
	text VARCHAR (1024) NOT NULL,
	creation_date TIMESTAMP NOT NULL,
	editing_date TIMESTAMP
);
