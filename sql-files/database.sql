create table if not exists roles (
  id varchar(15) primary key ,
  title varchar(45) not null
);

create table if not exists users (
  username varchar(45) primary key ,
  password varchar(60) ,
  first_name varchar(45) ,
  lastname_name varchar(45) ,
  phone_number varchar(10) ,
  email varchar(45) unique ,
  role_id varchar(15),
  foreign key (role_id) references roles(id)
);


create table if not exists departments (
  id varchar(15) primary key ,
  title varchar(45) not null
);

create table if not exists user_department (
  username varchar(45) ,
  department_id varchar(15) not null,
  foreign key (username) references users(username),
  foreign key (department_id) references departments(id)
);

create table if not exists student_status (
  username varchar(45) primary key ,
  status enum('undergraduate', 'graduate'),
  foreign key (username) references users(username)
);

create table if not exists companies (
  id int primary key ,
  title varchar(45) not null ,
  address varchar(45) ,
  phone_number varchar(10) unique not null ,
  email varchar(45) unique not null ,
  website varchar(45) unique
);

create table if not exists user_company (
  username varchar(45) primary key ,
  company_id int not null ,
  foreign key (username) references users(username) ,
  foreign key (company_id) references companies(id)
);

create table if not exists keywords (
  id int primary key ,
  title varchar(45) not null ,
  slug varchar(45) not null
);

create table if not exists categories (
  id int primary key ,
  title varchar(45) not null ,
  slug varchar(45) not null
);

create table if not exists classifieds (
  id int primary key ,
  title varchar(45) not null ,
  content blob not null ,
  company_id int not null ,
  category_id int not null ,
  foreign key (company_id) references companies(id),
  foreign key (category_id) references categories(id)
);

create table if not exists cvs (
  id int primary key ,
  username varchar(45) not null ,
  file_url varchar(60) not null ,
  foreign key (username) references users(username)
);

create table if not exists keyword_classified (
  keyword_id int not null ,
  classified_id int not null ,
  foreign key (keyword_id) references keywords(id) ,
  foreign key (classified_id) references classifieds(id)
);

create table if not exists keyword_cv (
  keyword_id int not null ,
  cv_id int not null ,
  foreign key (keyword_id) references keywords(id) ,
  foreign key (cv_id) references cvs(id)
);

