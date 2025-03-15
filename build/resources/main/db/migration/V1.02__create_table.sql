CREATE TABLE users (
    id bigint PRIMARY KEY,
    email text ,
    name character varying(256) NOT NULL,
    password character varying(256) NOT NULL
 );

create index on users(email);