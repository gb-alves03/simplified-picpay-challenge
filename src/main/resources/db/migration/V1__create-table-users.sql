create table users(
    id UUID primary key,
    name varchar(255) not null,
    document varchar(15) not null,
    email varchar(255) not null,
    password varchar(255) not null,
    balance decimal not null,
    user_type varchar(100) not null,

    unique(document, email)
);