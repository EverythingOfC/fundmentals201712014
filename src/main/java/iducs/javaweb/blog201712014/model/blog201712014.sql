create sequence seq_blog201712014 increment by 1 start with 1;

create table blog201712014
(
    id  number(11) not null primary key,
    author    varchar2(30) not null,
    email   varchar2(30) not null,
    title   varchar2(50),
    content varchar2(100),
    filepath varchar2(100),
    regdatetime date default sysdate
);

drop sequence seq_blog201712014;
drop table blog201712014;

select * from blog201712014;

commit;

delete from blog201712014;

select * from member1;
