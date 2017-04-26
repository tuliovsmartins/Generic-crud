DROP TABLE client;
CREATE TABLE client (id bigint NOT NULL, create_date datetime, document varchar(255), name varchar(255), PRIMARY KEY (id)) ENGINE=InnoDB DEFAULT CHARSET=latin1;
DROP TABLE products;
CREATE TABLE products (id bigint NOT NULL, create_date datetime, description text, name varchar(255), price decimal(10,2), quantity bigint, PRIMARY KEY (id)) ENGINE=InnoDB DEFAULT CHARSET=latin1;
