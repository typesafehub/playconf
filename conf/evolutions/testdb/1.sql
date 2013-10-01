# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table proposal (
  id                        bigint not null,
  title                     varchar(255),
  proposal                  varchar(1000),
  type                      integer,
  is_approved               boolean,
  keywords                  varchar(255),
  speaker_id                bigint,
  constraint ck_proposal_type check (type in (0,1)),
  constraint pk_proposal primary key (id))
;

create table registered_user (
  id                        bigint not null,
  name                      varchar(255),
  description               varchar(255),
  picture_url               varchar(255),
  twitter_id                varchar(255),
  registration_date         date,
  constraint pk_registered_user primary key (id))
;

create table speaker (
  id                        bigint not null,
  name                      varchar(255),
  email                     varchar(255),
  bio                       varchar(1000),
  twitter_id                varchar(255),
  picture_url               varchar(255),
  constraint pk_speaker primary key (id))
;

create sequence proposal_seq;

create sequence registered_user_seq;

create sequence speaker_seq;

alter table proposal add constraint fk_proposal_speaker_1 foreign key (speaker_id) references speaker (id) on delete restrict on update restrict;
create index ix_proposal_speaker_1 on proposal (speaker_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists proposal;

drop table if exists registered_user;

drop table if exists speaker;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists proposal_seq;

drop sequence if exists registered_user_seq;

drop sequence if exists speaker_seq;

