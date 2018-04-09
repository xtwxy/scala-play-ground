CREATE KEYSPACE stream
WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'}
  AND durable_writes = true;

USE stream;

CREATE TABLE signal_value_log(
    id int,
    ts timestamp,
    value blob,
    primary key(id)
);