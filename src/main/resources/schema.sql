drop table if exists dot CASCADE;
drop table if exists member CASCADE;
drop table if exists member_token CASCADE;
drop table if exists music CASCADE;
drop sequence if exists hibernate_sequence;

create sequence hibernate_sequence start 1 increment 1;

create table member (
                        member_id bigint not null,
                        email varchar(255) not null,
                        is_delete boolean not null,
                        member_name varchar(255) not null,
                        password varchar(255) not null,
                        role varchar(255) not null,
                        social_type varchar(255) not null,
                        profile_image_url varchar(255),
                        primary key (member_id)
);

create table dot (
                     dot_id bigint not null,
                     reg_time timestamp,
                     update_time timestamp,
                     created_by varchar(255),
                     modified_by varchar(255),
                     comment varchar(255),
                     latitude varchar(255) not null,
                     longitude varchar(255) not null,
                     region_nickname varchar(255) not null,
                     member_id bigint,
                     music_id bigint,
                     primary key (dot_id)
);


create table member_token (
                              member_token_id bigint not null,
                              reg_time timestamp,
                              update_time timestamp,
                              created_by varchar(255),
                              modified_by varchar(255),
                              refresh_token varchar(255),
                              token_expiration_time timestamp,
                              member_id bigint,
                              primary key (member_token_id)
);

create table music (
                       music_id bigint not null,
                       reg_time timestamp,
                       update_time timestamp,
                       created_by varchar(255),
                       modified_by varchar(255),
                       album_art clob not null,
                       music_name varchar(255) not null,
                       artist varchar(255) not null,
                       music_url varchar(255) not null,
                       site_type varchar(255) not null,
                       primary key (music_id)
);

alter table member
    add constraint UK_mbmcqelty0fbrvxp1q58dn57t unique (email);

alter table dot
    add constraint FKte27k22hnf93eyjphnuhlbyrv
        foreign key (member_id)
            references member;

alter table dot
    add constraint FK7e72vx3h1f05q3c4m9dheuwi7
        foreign key (music_id)
            references music;

alter table member_token
    add constraint FKt02uutgl1v2am5mshqqdk1cvd
        foreign key (member_id)
            references member;