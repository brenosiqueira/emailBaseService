
## Simple crud application using SpringBoot and Kotlin
# SpringBoot + Kotlin + RESTFul + JPA


--------------------------------------

# Prepare  database (postgres)

create table client_service (
    id BIGSERIAL primary key,
    name varchar(50),
    password varchar(50)
)

create table email_address(
    id BIGSERIAL primary key,
    key varchar(36),
    email varchar(50),
    mask varchar(50),
    uuid varchar(36),
    client_service_id BIGINT,
    constraint fk_email_address_with_client_service foreign key (client_service_id) references client_service(id)
)

insert into client_service (name, password) values ('appadmin','admin')


# Testing

### INSERT

curl -X POST -H "password: admin" -H "Content-Type:application/json" -d '{"key" : "lkj002", "email" : "zaraki@a.com.br"}' http://localhost:8080/base/email/v1/appadmin

### SELECT

curl -X GET -H "password: admin" http://localhost:8080/base/email/v1/appadmin/key/lkj002

### UPDATE

curl -X PUT -H "password: admin" -H "Content-Type:application/json" -d '{"email" : "kenpachi.zaraki@a.com.br"}' http://localhost:8080/base/email/v1/appadmin/key/lkj002

### DELETE

curl -X DELETE -H "password: admin" http://localhost:8080/base/email/v1/appadmin/key/lkj002