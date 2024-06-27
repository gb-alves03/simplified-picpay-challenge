create table transactions(
  id serial primary key,
  amount decimal not null,
  payer_id UUID not null,
  receiver_id UUID not null,
  realized_at timestamp without time zone,
  foreign key (payer_id) references users(id) on delete cascade,
  foreign key (receiver_id) references users(id) on delete cascade
);