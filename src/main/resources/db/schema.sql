CREATE TABLE role(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    note VARCHAR(150)
);

CREATE TABLE account(
    id BIGSERIAL PRIMARY KEY,
    role_id INTEGER REFERENCES role(id),
    email VARCHAR(100) NOT NULL UNIQUE,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(200) NOT NULL,
    avatar VARCHAR(255),
    birth_date DATE,
    sex VARCHAR(10),
    active BOOLEAN DEFAULT FALSE NOT NULL,
    created TIMESTAMP,
    updated TIMESTAMP
);

CREATE TABLE token(
    id BIGSERIAL PRIMARY KEY,
    account_id INTEGER REFERENCES account(id),
    token_hash VARCHAR(200) UNIQUE,
    token_type VARCHAR(50),
    ip_address VARCHAR(25),
    description VARCHAR(255),
    created TIMESTAMP,
    expiration TIMESTAMP
);

CREATE TABLE publisher(
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255),
    city VARCHAR(100),
    note VARCHAR(250),
    deleted BOOLEAN DEFAULT FALSE NOT NULL,
    created TIMESTAMP,
    updated TIMESTAMP
);

CREATE TABLE author(
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    website VARCHAR(100),
    note VARCHAR(250),
    deleted BOOLEAN DEFAULT FALSE NOT NULL,
    created TIMESTAMP,
    updated TIMESTAMP
);

CREATE TABLE category(
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    note VARCHAR(250),
    deleted BOOLEAN DEFAULT FALSE NOT NULL,
    created TIMESTAMP,
    updated TIMESTAMP
);

CREATE TABLE book(
    id UUID PRIMARY KEY,
    publisher_id UUID REFERENCES publisher(id),
    isbn INTEGER NOT NULL UNIQUE,
    title VARCHAR(150) NOT NULL,
    note VARCHAR(150),
    available BOOLEAN NOT NULL DEFAULT FALSE,
    deleted BOOLEAN DEFAULT FALSE NOT NULL,
    created TIMESTAMP,
    updated TIMESTAMP
);

CREATE TABLE author_book(
    author_id UUID REFERENCES author(id),
    book_id UUID REFERENCES book(id)
);

CREATE TABLE book_category(
    category_id UUID REFERENCES category(id),
    book_id UUID REFERENCES book(id)
);

CREATE TABLE borrowed_book(
    account_id INTEGER REFERENCES account(id),
    book_id UUID REFERENCES book(id),
    status VARCHAR(25),
    borrowed_date DATE,
    returned_date DATE,
    note VARCHAR(250)
);

INSERT INTO ROLE(name) VALUES('ADMIN'), ('MEMBER');