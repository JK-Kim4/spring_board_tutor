--=======================================================
--               SPRING BOOT TUTORIAL

--               스프링 부트를 활용한 게시판 만들기

--=======================================================
--
drop table member;
drop table board;
--


--회원 테이블
--용도 : 게시판 글 작성시 회원 로그인 필요
--회원 가입 시 입력받은 메일 주소로 인증번호 발송, 인증된 회원만 가입가능 

CREATE TABLE MEMBER (
    MEMBER_ID VARCHAR2(36) NOT NULL,
    PASSWORD VARCHAR2(300) NOT NULL,
    MEMBER_MAIL VARCHAR2(50) NOT NULL,
    ENABLED NUMBER DEFAULT 0 NOT NULL,
    
    CONSTRAINT PK_MEMBER PRIMARY KEY (MEMBER_ID)
);

CREATE TABLE BOARD (
    BOARD_NO NUMBER(2) NOT NULL,
    BOARD_WRITER VARCHAR2(36) NOT NULL,
    BOARD_TITLE VARCHAR2(50) NOT NULL,
    BOARD_CONTENT VARCHAR2(2500) NOT NULL,
    REG_DATE DATE DEFAULT SYSDATE NOT NULL,
    
    CONSTRAINTS PK_BOARD_NO PRIMARY KEY (BOARD_NO),
    CONSTRAINTS FK_BOARD_WRITER FOREIGN KEY(BOARD_WRITER)
                                                           REFERENCES MEMBER(MEMBER_ID)
                                                           ON DELETE SET NULL
);

