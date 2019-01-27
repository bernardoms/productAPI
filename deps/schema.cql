CREATE keyspace test with replication = {'class':'SimpleStrategy', 'replication_factor' : 1};
CREATE TABLE test.product(id text, name text, price double, PRIMARY KEY(id));

