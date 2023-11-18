CREATE TABLE book
(
    id              int                   primary  key generated always as identity,
    title           varchar               not null,
    topic           varchar               not null,
    author          varchar               not null,
    price           decimal               not null,
    pages           int                   not null,
    creation_date   date                  not null
);