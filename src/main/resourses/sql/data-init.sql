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

create table if not exists users(username varchar_ignorecase(50) not null primary key,password varchar_ignorecase(500) not null,enabled boolean not null);
create table if not exists authorities (username varchar_ignorecase(50) not null,authority varchar_ignorecase(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
create unique if not exists index ix_auth_username on authorities (username,authority);

delete from authorities;
delete from users;
insert into users (username, password, enabled) values ('admin','$2a$10$G2AqJqaGzrS9v89hLtP5PuIp92gzuNxBgtNavA936dnmo.UPjX6o.',true);
insert into authorities (username, authority) values ('admin','ROLE_ADMIN');
