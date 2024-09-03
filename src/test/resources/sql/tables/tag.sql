CREATE TABLE IF NOT EXISTS tag
(
	id SERIAL PRIMARY KEY,
	author_id INT REFERENCES app_user (id),
	name VARCHAR (255) UNIQUE NOT NULL,
	creation_date TIMESTAMP NOT NULL,
	editing_date TIMESTAMP
);
