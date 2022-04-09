/* password = admin */
insert into user(username, first_name, last_name, email, birthday, password, sex)
    value ('admin', 'Ivan', 'Petrov', 'some_email@gmail.com', '1990-04-07',
           '$2a$10$BFkxNp6nhQfO27Y9sDA5QuHAJ8xRhimrp0syljm1Gd6WcgVb1Y5/O', 'MAN');


insert into users_roles(user_id,role_id) value('1','1');
insert into users_roles(user_id,role_id) value('1','2');