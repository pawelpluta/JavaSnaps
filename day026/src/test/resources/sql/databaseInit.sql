create table accounts (
    account_id varchar(36) primary key,
    balance decimal
);

create table events_outbox (
    event_id varchar(36) primary key,
    body text,
    sent boolean
);