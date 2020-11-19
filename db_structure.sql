
DROP TABLE book IF EXISTS;
DROP TABLE book_identification IF EXISTS;
DROP TABLE book_identification_map IF EXISTS;
DROP TABLE customer IF EXISTS;
DROP TABLE discount IF EXISTS;
DROP TABLE manager IF EXISTS;
DROP TABLE shopping_cart IF EXISTS;
DROP TABLE transaction IF EXISTS;
DROP TABLE visit IF EXISTS;
DROP TABLE token IF EXISTS;
DROP TABLE token_status IF EXISTS;
DROP TABLE token_status_change IF EXISTS;




-- manager users
CREATE TABLE IF NOT EXISTS manager(
id serial PRIMARY KEY,
username TEXT not null,
password TEXT not null,
role TEXT not null
);


-- book
CREATE TABLE IF NOT EXISTS book(
id serial PRIMARY KEY,
title Text not null,
author Text not null ,
price float  not null

);


--token status
CREATE TABLE IF NOT EXISTS token_status(
id serial PRIMARY KEY not null,
status TEXT not null
);

--token users
CREATE TABLE IF NOT EXISTS token(
id serial PRIMARY KEY,
username TEXT UNIQUE not null,
password TEXT not null,
valid boolean  not null DEFAULT FALSE,
token_status_id integer REFERENCES token_status(id),
created_at TIMESTAMP WITH TIME ZONE not null DEFAULT CURRENT_TIMESTAMP
);

-- shopping cart
CREATE TABLE IF NOT EXISTS shopping_cart(
id serial PRIMARY KEY,
customer_id  integer REFERENCES customer(id),
book_id  integer REFERENCES book(id)

);

-- discount
CREATE TABLE IF NOT EXISTS discount(
id serial PRIMARY KEY,
type TEXT not null,
promotion double not null,
valid boolean not null DEFAULT TRUE
);


-- book identification type
CREATE TABLE IF NOT EXISTS book_identification_type(
id serial PRIMARY KEY,
count int not null,
stop_order boolean  not null DEFAULT TRUE
);


-- Transaction
CREATE TABLE IF NOT EXISTS transaction(
id serial PRIMARY KEY,
shopping_id  integer REFERENCES shopping_cart(id)
);

-- customer
CREATE TABLE IF NOT EXISTS customer(
id serial PRIMARY KEY,
user_name TEXT UNIQUE not null,
password TEXT not null,
postal_address TEXT ,
role Text not null,
created_at TIMESTAMP WITH TIME ZONE not null DEFAULT CURRENT_TIMESTAMP,
discount_id  integer REFERENCES discount(id)
);


-- token status change
CREATE TABLE IF NOT EXISTS token_status_change(
id serial PRIMARY KEY,
token_id integer REFERENCES token(id),
token_status_id integer REFERENCES token_status(id),
changed_at TIMESTAMP WITH TIME ZONE not null DEFAULT CURRENT_TIMESTAMP
);


-- book identification map
CREATE TABLE IF NOT EXISTS book_identification_map(
id serial PRIMARY KEY,
book_id integer REFERENCES book(id),
book_identification_id integer REFERENCES book_identification(id)
);



-- visit
CREATE TABLE IF NOT EXISTS visit(
id serial PRIMARY KEY,
customer_id integer REFERENCES customer(id),
book_id integer REFERENCES book(id),
book_identification_id serial REFERENCES book_identification(id),
checked_in TIMESTAMP not null,
checked_out TIMESTAMP,
token_id integer REFERENCES token(id),
manager_id integer REFERENCES manager(id)
);
