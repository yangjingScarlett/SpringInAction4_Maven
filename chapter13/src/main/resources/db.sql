create table SPITTER
(
  id              NUMBER not null PRIMARY KEY ,
  username        VARCHAR2(25) not null,
  password        VARCHAR2(25) not null,
  fullname        VARCHAR2(100) not null,
  email           VARCHAR2(50) not null,
  update_by_email CHAR(1) not null,
  status          VARCHAR2(10)
);

create table SPITTLE
(
  id        NUMBER not null PRIMARY KEY ,
  spitter   NUMBER not null,
  message   VARCHAR2(500) not null,
  post_time DATE not null
);

alter table SPITTLE
  add constraint SPITTLE_SPITTER_ID foreign key (SPITTER)
  references SPITTER (ID) on delete cascade;

insert into spitter (ID, USERNAME, PASSWORD, FULLNAME, EMAIL, UPDATE_BY_EMAIL, STATUS)
values (1, 'habuma', 'password', 'Craig Walls', 'craig@habuma.com', 'N', 'Newbie');
insert into spitter (ID, USERNAME, PASSWORD, FULLNAME, EMAIL, UPDATE_BY_EMAIL, STATUS)
values (2, 'mwalls', 'password', 'Michael Walls', 'mwalls@habuma.com', 'Y', 'Newbie');
insert into spitter (ID, USERNAME, PASSWORD, FULLNAME, EMAIL, UPDATE_BY_EMAIL, STATUS)
values (3, 'chuck', 'password', 'Chuck Wagon', 'chuck@habuma.com', 'N', 'Newbie');
insert into spitter (ID, USERNAME, PASSWORD, FULLNAME, EMAIL, UPDATE_BY_EMAIL, STATUS)
values (4, 'artnames', 'password', 'Art Names', 'art@habuma.com', 'Y', 'Newbie');

insert into spittle (ID, SPITTER, MESSAGE, POST_TIME)
values (1, 1, 'This is a test spittle message', to_date('09-06-2012 22:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into spittle (ID, SPITTER, MESSAGE, POST_TIME)
values (2, 1, 'This is another test spittle message', to_date('04-07-2012 23:30:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into spittle (ID, SPITTER, MESSAGE, POST_TIME)
values (3, 1, 'This is a third test spittle message', to_date('25-03-2012 12:15:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into spittle (ID, SPITTER, MESSAGE, POST_TIME)
values (4, 2, 'Hello from Chuck!', to_date('25-03-2012 12:15:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into spittle (ID, SPITTER, MESSAGE, POST_TIME)
values (5, 4, 'Hello from Art!', to_date('25-03-2012 12:15:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into spittle (ID, SPITTER, MESSAGE, POST_TIME)
values (6, 4, 'Hello again from Art!', to_date('25-03-2012 12:25:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into spittle (ID, SPITTER, MESSAGE, POST_TIME)
values (7, 4, 'Hola from Arthur!', to_date('25-03-2012 12:35:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into spittle (ID, SPITTER, MESSAGE, POST_TIME)
values (8, 4, 'Buenos Dias from Art!', to_date('25-03-2012 12:45:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into spittle (ID, SPITTER, MESSAGE, POST_TIME)
values (9, 4, 'Ni Hao from Art!', to_date('25-03-2012 12:55:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into spittle (ID, SPITTER, MESSAGE, POST_TIME)
values (10, 4, 'Guten Tag from Art!', to_date('25-03-2012 13:05:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into spittle (ID, SPITTER, MESSAGE, POST_TIME)
values (11, 4, 'Konnichi wa from Art!', to_date('25-03-2012 13:15:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into spittle (ID, SPITTER, MESSAGE, POST_TIME)
values (12, 4, 'Buon giorno from Art!', to_date('25-03-2012 13:25:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into spittle (ID, SPITTER, MESSAGE, POST_TIME)
values (13, 4, 'Bonjour from Art!', to_date('25-03-2012 13:35:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into spittle (ID, SPITTER, MESSAGE, POST_TIME)
values (14, 4, 'Aloha from Art!', to_date('25-03-2012 13:45:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into spittle (ID, SPITTER, MESSAGE, POST_TIME)
values (15, 4, 'God dag from Art!', to_date('25-03-2012 13:55:00', 'dd-mm-yyyy hh24:mi:ss'));



