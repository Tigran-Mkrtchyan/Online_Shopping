create database shop;

\c shop;

create table products (
 id serial primary key,
 name varchar(30) not null
);

create table product_details (
 id serial primary key,
 product_id int not null,
 price int not null,
 description text,
 foreign key (product_id) references products(id) on delete cascade on update cascade
);

insert into products (name)
values  ('Coca Cola'),
 	    ('Skittles'),
 		('kinder Joy'),
 		('Joker Hot'),
 		('Grean Energy'),
 		('Raffaello');

insert into product_details (product_id, price)
values 	 (1,400),
		 (2,240),
		 (3,600),
 		 (6,2200);

insert into product_details (product_id, price,description)
values   (4,500,'it`s an energy drink'),
		 (5,600,'it`s an energy drink');