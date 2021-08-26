create table file
(
    id            bigint not null,
    extension     varchar(255),
    generate_name varchar(255),
    origin_name   varchar(255),
    user_id       bigint,
    primary key (id)
) engine=InnoDB

create table hibernate_sequence
(
    next_val bigint
) engine=InnoDB

create table role
(
    id   bigint not null auto_increment,
    name varchar(255),
    primary key (id)
) engine=InnoDB

create table user
(
    id         bigint not null auto_increment,
    email      varchar(255),
    first_name varchar(255),
    last_name  varchar(255),
    password   varchar(255),
    primary key (id)
) engine=InnoDB

create table user_files
(
    user_id  bigint not null,
    files_id bigint not null,
    primary key (user_id, files_id)
) engine=InnoDB

create table users_roles
(
    user_id bigint not null,
    role_id bigint not null
) engine=InnoDB

alter table user
drop
index UKob8kqyqqgmefl0aco34akdtpe

alter table user
    add constraint UKob8kqyqqgmefl0aco34akdtpe unique (email)