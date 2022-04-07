----------------------------�������� ���α׷�-------------------------------------


---1. ȸ�� ���� ���̺� ����(t_member)
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



--2. å ���� ���̺� ���� (t_books)
create table t_books(
    b_no int primary key
    ,title varchar2(70) not null
    ,author varchar2(30) not null
    ,pub varchar2(30) not null
    ,pub_d date not null
    ,status int default 1
);

--1000������ �����ϴ� å ��ȣ�� �������� �����Ͽ� �ο�
create sequence seq_t_books_no start with 1000;

select * from t_books;

select b_no, title, author, pub, to_char(pub_d,'yyyy-mm-dd') as pub_d, status 
    from t_books
    where instr(b_no, 100) != 0;



--3. ���� ���� ���̺� ���� (t_rental)
--t_member�� id, t_books�� b_no �� �ܷ�Ű�� �����Ͽ� ���� ����� ������ ���̺� ����
create table t_rental(
    ren_no int primary key
    ,id varchar2(30) not null
    ,b_no int not null
    ,ren_d date default sysdate
    ,return_d date default sysdate+14
    
    ,constraint fk_t_rental_id foreign key(id) references t_member(id)
    ,constraint fk_t_rental_bno foreign key(b_no) references t_books(b_no)
);

--���� ��ȣ�� �������� �����Ͽ� �ο�
create sequence seq_t_ren_no;

select * from t_rental;


--���� �� �� ���̺��� �����Ͽ� ���ϴ� ���� �˻� (id�� serin�� ȸ���� �뿩���� ��ȸ)
select r.b_no, title,author, ren_d, return_d
  from t_rental r, t_books b
 where id = 'serin'
     and r.b_no = b.b_no;


--����
insert into t_rental values(seq_t_ren_no.nextval, 'serin', 1515, sysdate, sysdate+7);



--��¥ ���ϴ� ���·� ����ϱ� -> to_char �̿�
select to_char(sysdate, 'yyyy/mm/dd') as d from dual;
select sysdate from dual;

desc t_books;

select * from t_books;