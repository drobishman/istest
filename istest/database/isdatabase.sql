DROP DATABASE is_test;
CREATE DATABASE is_test;
USE is_test;
CREATE TABLE users (
id int(6) NOT NULL AUTO_INCREMENT, 
login varchar(50) NOT NULL, 
password varchar(20) NOT NULL, 
firstname char(20) NOT NULL, 
lastname char(20) NOT NULL, 
phone double NOT NULL, 
email char(50) NOT NULL, 
agent_id int(6), 
PRIMARY KEY (id),
FOREIGN KEY (agent_id) REFERENCES users(id) ON DELETE SET NULL
);
CREATE TABLE roles (
id int(6) NOT NULL AUTO_INCREMENT, 
role char(20) NOT NULL, 
PRIMARY KEY (id)
);
CREATE TABLE user_roles (
user_id int(6) NOT NULL, 
role_id int(6) NOT NULL,
FOREIGN KEY (user_id) REFERENCES users(id),
FOREIGN KEY (role_id)  REFERENCES roles(id)
);
CREATE TABLE search (
user_id int(6) NOT NULL,
id int(6) NOT NULL AUTO_INCREMENT, 
province char(20), city char(20), 
zone char(20),
rooms int(6),
bath int(6),
price_min int(6), 
price_max int(6),
sqm_min int(6), 
sqm_max int(6), 
type char(20), 
subtype char(20), 
PRIMARY KEY (id),
FOREIGN KEY (user_id)  REFERENCES users(id)
);
CREATE TABLE property (
id int(6) NOT NULL AUTO_INCREMENT, 
province char(20) NOT NULL, 
city char(20) NOT NULL, 
zone char(20) NOT NULL, 
rooms int(6) NOT NULL, 
bath int(6) NOT NULL, 
type char(20) NOT NULL, 
subtype char(20) NOT NULL, 
price int(6) NOT NULL, 
sqm int(6) NOT NULL,
description char(50) NOT NULL, 
foto_link char(50), 
PRIMARY KEY (id)
);
CREATE TABLE result (
search_id int(6) NOT NULL, 
property_id int(6) NOT NULL,
FOREIGN KEY (search_id) REFERENCES search(id),
FOREIGN KEY (property_id)  REFERENCES property(id)
);
CREATE TABLE bookmark (
user_id int(6) NOT NULL,
property_id int(6) NOT NULL,
FOREIGN KEY (user_id)  REFERENCES users(id),
FOREIGN KEY (property_id)  REFERENCES property(id)
);

insert into roles values (null,'administrator');
insert into roles values (null,'agent');
insert into roles values (null,'user');
insert into users values(null,'adrian.drob88@gmail.com','ciao','adrian','drob',3202595751,'adrian.drob88@gmail.com',null);
insert into users values(null,'ameluzio89@gmail.com','ciao','alessandro','meluzio',3384446790,'ameluzio89@gmail.com',1);
insert into user_roles values (1,1);
insert into user_roles values (2,2);
insert into property values(null,"RM","Roma","none",3,2,"residenziale","appartamento",50000,80,"Appartamento da ristrutturare","http://generico");
insert into property values(null,"AG","Agrigento","none",3,2,"commerciale","negozio",80000,90,"Locale da ristrutturare","http://generico");
insert into property values(null,"AG","Agrigento","none",3,2,"commerciale","negozio",60000,85,"Locale da buttare","http://generico");
insert into property values(null,"BO","Bronte","none",3,2,"residenziale","monolocale",300000,75,"Abitazione completa","http://generico");
insert into property values(null,"MI","Meta","none",3,2,"residenziale","villa",190000,60,"Villa in campagna da ristrutturare","http://generico");
insert into bookmark values(1,1);
