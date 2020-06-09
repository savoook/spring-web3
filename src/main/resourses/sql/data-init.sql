DELETE FROM COURSE;

INSERT INTO COURSE (NAME, DURATION) VALUES ('Java-1', 10);
INSERT INTO COURSE (NAME, DURATION) VALUES ('Java-2', 20);
INSERT INTO COURSE (NAME, DURATION) VALUES ('Java-3', 30);
INSERT INTO COURSE (NAME, DURATION) VALUES ('Java-4', 40);
INSERT INTO COURSE (NAME, DURATION) VALUES ('Java-5', 50);
INSERT INTO COURSE (NAME, DURATION) VALUES ('Java-6', 60);
INSERT INTO COURSE (NAME, DURATION) VALUES ('Java-7', 70);
INSERT INTO COURSE (NAME, DURATION) VALUES ('Java-8', 80);
INSERT INTO COURSE (NAME, DURATION) VALUES ('Java-9', 90);

create table users(username varchar_ignorecase(50) not null primary key,password varchar_ignorecase(500) not null,enabled boolean not null);
create table authorities (username varchar_ignorecase(50) not null,authority varchar_ignorecase(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);