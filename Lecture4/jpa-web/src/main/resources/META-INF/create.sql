create sequence hibernate_sequence start with 1 increment by 1
create table AUTHOR (ID integer not null, email varchar(255) not null, firstname varchar(255) not null, lastname varchar(255) not null, primary key (ID))
create table POST (ID integer not null, content clob not null, published boolean not null, title varchar(255) not null, author_id integer not null, primary key (ID))
create table POST_TAG (post_id integer not null, tag_id integer not null, primary key (post_id, tag_id))
create table TAG (ID integer not null, name varchar(255) not null, visible boolean not null, primary key (ID))
alter table AUTHOR add constraint UK_f7gncwa3jekapqh48kf23bbts unique (email)
alter table TAG add constraint UK_q8beoh2i5vhonh3itrfjsmunn unique (name)
alter table POST add constraint FK7d8xtm26wx2kgacfavd46v8rq foreign key (author_id) references AUTHOR
alter table POST_TAG add constraint FK2nd3649tur2dtbghwm7hu1kfa foreign key (tag_id) references TAG
alter table POST_TAG add constraint FKnjdgyr1qvsbifp3vt75o04ovs foreign key (post_id) references POST