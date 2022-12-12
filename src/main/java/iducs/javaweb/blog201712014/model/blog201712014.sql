create sequence seq_blog201712014 increment by 1 start with 1;

create table blog201712014
(
    id  number(11) not null primary key,
    name    varchar2(30) not null,
    email   varchar2(30) not null,
    title   varchar2(50),
    content varchar2(100)
);

drop sequence seq_blog201712014;
drop table blog201712014;

insert into blog201712014 values(seq_blog201712014.nextval, 'sw', 'sw@induk.ac.kr', '블로그제목', '블로그내용');

update blog200412345 set name='강아지', email='dog@dog', title='인덕대학교', content='멍멍' where id='2';

commit;
/*
update blog200412345 set name=?, email=?, title=?, content=? where id=?;
*/

select * from blog201712014;
