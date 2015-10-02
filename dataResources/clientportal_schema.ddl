alter table t_department drop foreign key FKEFEB87FDCD79881D;
alter table t_user drop foreign key FKCB63CCB6D5C5339E;
alter table t_user drop foreign key FKCB63CCB682396D1D;
drop table if exists t_user_normal_address;
drop table if exists t_user_status;
drop table if exists t_user_login_info;
drop table if exists t_role;
drop table if exists t_menu_item;
drop table if exists t_department;
drop table if exists t_user;
drop table if exists t_audit_logs;
drop table if exists t_company;
create table t_user_normal_address (
   id varchar(32) not null,
   city varchar(255),
   country varchar(255),
   province varchar(255),
   street varchar(255),
   zipcode varchar(255),
   primary key (id)
);
create table t_user_status (
   id varchar(32) not null,
   description varchar(255),
   comments varchar(255),
   primary key (id)
);
create table t_user_login_info (
   id varchar(32) not null,
   comments varchar(255),
   computer_ip varchar(255),
   computer_name varchar(255),
   login_time datetime,
   primary key (id)
);
create table t_role (
   id varchar(32) not null,
   comments varchar(255),
   description varchar(255),
   primary key (id)
);
create table t_menu_item (
   id varchar(32) not null,
   name varchar(30) not null,
   parent_name varchar(30),
   action varchar(255),
   align varchar(10),
   altImage varchar(30),
   description varchar(50),
   forward varchar(50),
   width varchar(5),
   height varchar(5),
   image varchar(50),
   location varchar(255),
   onclick varchar(255),
   onmouseout varchar(255),
   onmouseover varchar(255),
   page varchar(255),
   roles varchar(100),
   target varchar(10),
   title varchar(30),
   toolTip varchar(100),
   url varchar(255),
   primary key (id)
);
create table t_department (
   id varchar(32) not null,
   name varchar(255),
   comments varchar(255),
   company_id varchar(32),
   primary key (id)
);
create table t_user (
   id varchar(32) not null,
   user_id varchar(10) not null,
   user_name varchar(30) not null,
   user_password varchar(10),
   firstName varchar(30),
   lastName varchar(30),
   userstatus_id varchar(32),
   birthday date,
   comments varchar(255),
   born_date varchar(255),
   degree varchar(255),
   e_mail varchar(255),
   gendar varchar(255),
   homeTelePhone varchar(255),
   id_card varchar(255),
   major varchar(255),
   mobilePhone varchar(255),
   normal_address_id varchar(32),
   primary key (id)
);
create table t_audit_logs (
   id varchar(32) not null,
   created_time date not null,
   entityClass varchar(30),
   entityId varchar(32),
   message varchar(255),
   primary key (id)
);
create table t_company (
   id varchar(32) not null,
   name varchar(255),
   comments varchar(255),
   primary key (id)
);
alter table t_department add index FKEFEB87FDCD79881D (company_id), add constraint FKEFEB87FDCD79881D foreign key (company_id) references t_company (id);
alter table t_user add index FKCB63CCB6D5C5339E (normal_address_id), add constraint FKCB63CCB6D5C5339E foreign key (normal_address_id) references t_user_normal_address (id);
alter table t_user add index FKCB63CCB682396D1D (userstatus_id), add constraint FKCB63CCB682396D1D foreign key (userstatus_id) references t_user_status (id);
