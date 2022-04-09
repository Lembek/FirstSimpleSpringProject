create table hibernate_sequence (next_val bigint);
insert into hibernate_sequence values ( 1 );
create table message
(
    id        bigint auto_increment not null,
    date      datetime(6),
    tag       varchar(255),
    text      varchar(2048),
    author_id bigint,
    primary key (id)
);
create table role
(
    id   bigint not null,
    name varchar(255),
    primary key (id)
);
create table user
(
    id         bigint auto_increment not null,
    birthday   date   not null,
    email      varchar(255) not null,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    password   varchar(255) not null,
    sex        varchar(255) not null,
    username   varchar(255) not null,
    primary key (id)
);
create table users_roles
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id)
);
alter table message
    add constraint message_user_fk foreign key (author_id) references user (id);
alter table users_roles
    add constraint user_role_role_fk foreign key (role_id) references role (id);
alter table users_roles
    add constraint user_role_user_fk foreign key (user_id) references user (id);
