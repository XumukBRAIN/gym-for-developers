CREATE TABLE book
(
    id              int primary key,
    title           varchar               not null,
    topic           varchar               not null,
    author          varchar               not null,
    price           decimal               not null,
    pages           int check             not null,
    creation_date   date                  not null
);