CREATE TABLE registration
( 
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 first_name varchar(250) NOT NULL,
 last_name varchar(250) NOT NULL,
 username varchar(250) NOT NULL,
 email_id varchar(250) NOT NULL,
 password varchar(250) NOT NULL,
 confirm_password varchar(250) NOT NULL
);

insert into registration (first_name, last_name, username, email_id, password, confirm_password)
VALUES ('Cardinal', 'Tom B. Erichsen', 'Skagen 21', 'Stavanger', '4006', 'Norway');