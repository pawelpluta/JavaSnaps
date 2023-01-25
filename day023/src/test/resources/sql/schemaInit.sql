create table accounts (
    account_id varchar(36) primary key,
    balance decimal
);

alter table accounts add column event_ids varchar(36)[];