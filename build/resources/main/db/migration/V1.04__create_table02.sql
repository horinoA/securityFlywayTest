
CREATE TABLE verificationTokens (
    token character varying(256) PRIMARY KEY,
    userId bigint NOT NULL,
    expiryDate timestamp with time zone NOT NULL
 );

create unique index on verificationTokens(token);

CREATE TABLE roles (
    id integer NOT NULL,
    rolename character varying(256) NOT NULL
);

create unique index on roles(id);

CREATE TABLE userRole (
    id bigint NOT NULL,
    userId bigint NOT NULL,
    roleId integer
);

create index on userRole(userId);

insert into users (id,email,name,password) VALUES(
    217502269585754143
    ,'admin@example.com'
    ,'管理太郎'
    ,'$2a$12$KFFoYhfSgunepip3mUjEVuVxhnt.vqeamHTzbY1GLcP9gQjLn1ZT6'
 );

 insert into roles (id, rolename) VALUES(
 10,'ROLE_GENERAL'),(
 20,'ROLE_ADMIN'),(
 30,'ROLE_USER');

 insert into userRole (id, userId, roleId) VALUES
 (184133077503576093,217502269585754143,	30),
 (184133484585945118,217502269585754143,	20)
 ;