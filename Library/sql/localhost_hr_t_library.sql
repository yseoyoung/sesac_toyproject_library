----------------------------도서관리 프로그램-------------------------------------


---1. 회원 관리 테이블 생성(t_member)
create table t_member(
    id varchar2(30) primary key
    ,password varchar2(30) not null
    ,name varchar2(30) not null
    ,email varchar2(50) not null
    ,isAdmin int default 1
    ,joindate date default sysdate
);

select * from t_member;

select id, name, email, joindate, isAdmin from t_member where isAdmin=1;

select id, name, email, to_char(joindate, 'yyyy-mm-dd') as joindate from t_member where isAdmin=1;



--2. 책 관리 테이블 생성 (t_books)
create table t_books(
    b_no int primary key
    ,title varchar2(70) not null
    ,author varchar2(30) not null
    ,pub varchar2(30) not null
    ,pub_d date not null
    ,status int default 1
);

--1000번부터 시작하는 책 번호를 시퀀스를 생성하여 부여
create sequence seq_t_books_no start with 1000;

select * from t_books;

select b_no, title, author, pub, to_char(pub_d,'yyyy-mm-dd') as pub_d, status 
    from t_books
    where instr(b_no, 100) != 0;



--3. 대출 관리 테이블 생성 (t_rental)
--t_member의 id, t_books의 b_no 를 외래키로 설정하여 대출 기록을 저장할 테이블 생성
create table t_rental(
    ren_no int primary key
    ,id varchar2(30) not null
    ,b_no int not null
    ,ren_d date default sysdate
    ,return_d date default sysdate+14
    
    ,constraint fk_t_rental_id foreign key(id) references t_member(id)
    ,constraint fk_t_rental_bno foreign key(b_no) references t_books(b_no)
);

--정렬 번호는 시퀀스를 생성하여 부여
create sequence seq_t_ren_no;

select * from t_rental;


--대출 시 두 테이블을 조인하여 원하는 정보 검색 (id가 serin인 회원의 대여정보 조회)
select r.b_no, title,author, ren_d, return_d
  from t_rental r, t_books b
 where id = 'serin'
     and r.b_no = b.b_no;


--대출
insert into t_rental values(seq_t_ren_no.nextval, 'serin', 1515, sysdate, sysdate+7);



--날짜 원하는 형태로 출력하기 -> to_char 이용
select to_char(sysdate, 'yyyy/mm/dd') as d from dual;
select sysdate from dual;

desc t_books;

select * from t_books;