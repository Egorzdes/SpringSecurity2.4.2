
insert into users (id, Age, LastName, Name, password) values (1, 1, 'last', 'ADMIN', 'ADMIN' );
insert into users (id, Age, LastName, Name, password) values (2, 2, 'last', 'USER', 'USER' );

insert into roles (id, role) values (1,'ROLE_ADMIN' );
insert into roles (id, role) values (2,'ROLE_USER' );

insert into users_roles (user_id, roles_id) values (1, 1);
insert into users_roles (user_id, roles_id) values (1, 2);
insert into users_roles (user_id, roles_id) values (2, 2);