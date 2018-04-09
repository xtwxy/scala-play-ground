CREATE KEYSPACE stream
WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'}
  AND durable_writes = true;

USE stream;

CREATE TABLE signal_value_log(
    id varchar,
    ts timestamp,
    v blob,
    primary key(id, ts)
);
