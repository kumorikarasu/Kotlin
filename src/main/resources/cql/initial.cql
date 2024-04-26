

CREATE KEYSPACE IF NOT EXISTS hello WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 1};

DROP TABLE IF EXISTS hello.hello;
