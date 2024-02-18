# db setup

Create schema if not exists `hb-one-to-one`;
use `hb-one-to-one`;

set FOREIGN_KEY_CHECKS = 0;

drop table if exists `instructor_detail`;

create table `instructor_detail`
(
    id              int not null auto_increment,
    youtube_channel varchar(128) default null,
    hobby           varchar(45)  default null,
    primary key (id)
);

drop table if exists instructor;

create table instructor
(
    id                   int not null auto_increment,
    first_name           varchar(45) default null,
    last_name            varchar(45) default null,
    email                varchar(50) default null,
    instructor_detail_id int         default null,
    primary key (id),
    -- key `FK_DETAIL_idx` (`instructor_detail_id`),
    constraint `FK_DETAIL` foreign key (`instructor_detail_id`)
        references `instructor_detail` (id) on delete no action on update no action

);

select *
from course;
drop table if exists course;
create table course
(
    id            int not null auto_increment primary key,
    title         varchar(128) default null,
    instructor_id int          default null,
    unique key `title_unique` (title),
    foreign key (instructor_id) references instructor (id) on delete no action on update no action
);

drop table if exists review;
create table review
(
    id        int not null auto_increment primary key,
    `comment` varchar(256) default null,
    course_id int          default null,
    foreign key (course_id)
        references course (id) on delete no action on update no action
);

select *
from course;

drop table if exists student;

create table student
(
    id         int not null auto_increment primary key,
    first_name varchar(45) default null,
    last_name  varchar(45) default null,
    email      varchar(50) default null
);

create table course_student
(
    course_id  int not null,
    student_id int not null,

    primary key (course_id, student_id),
    foreign key (course_id) references course (id)
        on update no action on delete no action,
    foreign key (student_id) references student (id)
        on update no action on delete no action
);

set FOREIGN_KEY_CHECKS = 1;