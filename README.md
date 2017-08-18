# JavaEE Playground
Show how to build JavaEE 7 (Soon JavaEE 8) web applications with theme `library`. Primarily designed
for Glassfish / Payara.

## Environment
This project was designed to deploy into Payara Full Server 172  with PostgreSQL 9.6.
I prepare the database schema ~~(soon)~~ on `src/main/resources/db/schema.sql`, and will fully use JDBC Connection Pool from Payara.
Below is how the environment configured:

### Create PostgreSQL Database
Prepare user and database on PostgreSQL.

```
CREATE USER demo WITH PASSWORD 'password';
CREATE DATABASE javaee_playground OWNER demo ENCODING 'UTF-8';
GRANT ALL PRIVILEGES ON DATABASE javaee_playground TO demo;
```

To execute database schema from command line:

```
psql -U demo -d javaee_playground -a -f src/main/resources/db/schema.sql
```

### Add PostgreSQL JDBC driver
Download [PostgreSQL jdbc driver](https://jdbc.postgresql.org/download/postgresql-42.1.4.jre6.jar) 
and put it into `${PAYARA_HOME}/glassfish/domains/${YOUR_DOMAIN}/lib`

```
curl -o ${PAYARA_HOME}/glassfish/domains/${PAYARA_DOMAIN}/lib/postgresql-41.1.4.jar -L https://jdbc.postgresql.org/download/postgresql-42.1.4.jre6.jar
```

### Create JDBC Resource and Pool on Payara
To be able execute Payara command make sure your working directory on `${PAYARA_PATH}/bin`.
Make sure your Payara is running before execute this command.

```
./asadmin start-domain ${PAYARA_DOMAIN}
```

* Create JDBC Pool

```
./asadmin create-jdbc-connection-pool \
--datasourceclassname org.postgresql.ds.PGConnectionPoolDataSource \
--restype javax.sql.ConnectionPoolDataSource \
--property User=demo:Password=password:DatabaseName=javaee_playground:ServerName=localhost:PortNumber=5432 JavaeePlayground
```

* Create JDBC Resource

```
./asadmin create-jdbc-resource --connectionpoolid JavaeePlayground jdbc/javaee/playground
```
