# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table game (
  id                        bigint not null,
  title                     varchar(255),
  release                   timestamp,
  developer_name            varchar(255),
  constraint pk_game primary key (id))
;

create table user (
  name                      varchar(255) not null,
  email                     varchar(255),
  password                  varchar(255),
  constraint uq_user_email unique (email),
  constraint pk_user primary key (name))
;

create sequence game_seq;

create sequence user_seq;

alter table game add constraint fk_game_developer_1 foreign key (developer_name) references user (name) on delete restrict on update restrict;
create index ix_game_developer_1 on game (developer_name);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists game;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists game_seq;

drop sequence if exists user_seq;

