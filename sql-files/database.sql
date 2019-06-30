create database if not exists career_office;

create table if not exists roles
(
    id    varchar(15) primary key,
    title varchar(45) not null
);

create table if not exists users
(
    username     varchar(45) primary key,
    password     varchar(100) not null,
    first_name   varchar(45) not null,
    last_name    varchar(45) not null,
    phone_number varchar(10),
    email        varchar(45) unique not null,
    role_id      varchar(15) not null,
    foreign key (role_id) references roles (id)
);


create table if not exists departments
(
    id    varchar(15) primary key,
    title varchar(45) not null
);

create table if not exists user_department
(
    username      varchar(45) primary key,
    department_id varchar(15) not null,
    foreign key (username) references users (username),
    foreign key (department_id) references departments (id)
);

create table if not exists companies
(
    id           varchar(10) primary key,
    title        varchar(45) unique not null,
    address      varchar(45) not null,
    phone_number varchar(10) unique not null,
    email        varchar(45) unique not null,
    website      varchar(45) unique
);

create table if not exists user_company
(
    username   varchar(45) primary key,
    company_id varchar(10) not null,
    foreign key (username) references users (username),
    foreign key (company_id) references companies (id)
);

create table if not exists keywords
(
    id    int auto_increment primary key,
    title varchar(45) unique not null,
    slug  varchar(45) unique not null
);

create table if not exists categories
(
    id    int auto_increment primary key,
    title varchar(45) unique not null,
    slug  varchar(45) unique not null
);

create table if not exists classifieds
(
    id          int auto_increment primary key,
    title       varchar(45) not null,
    content     blob        not null,
    company_id  varchar(10) not null,
    category_id int         not null,
    foreign key (company_id) references companies (id),
    foreign key (category_id) references categories (id)
);

create table if not exists cvs
(
    id       int auto_increment primary key,
    username varchar(45) unique not null,
    file_url varchar(60),
    foreign key (username) references users (username)
);

create table if not exists keyword_classified
(
    keyword_id    int not null,
    classified_id int not null,
    primary key (keyword_id, classified_id),
    foreign key (keyword_id) references keywords (id),
    foreign key (classified_id) references classifieds (id)
);

create table if not exists keyword_cv
(
    keyword_id int not null,
    cv_id      int not null,
    primary key (keyword_id, cv_id),
    foreign key (keyword_id) references keywords (id),
    foreign key (cv_id) references cvs (id)
);

