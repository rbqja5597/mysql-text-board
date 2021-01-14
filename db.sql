# DB 생성
DROP DATABASE IF EXISTS textBoard;
CREATE DATABASE textBoard;
USE textBoard;

# 게시물 테이블 생성
CREATE TABLE article (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    title CHAR(200) NOT NULL,
    `body` TEXT NOT NULL,
    memberId INT(10) UNSIGNED NOT NULL,
    boardId INT(10) UNSIGNED NOT NULL
);

# 게시물 데이터 4개 생성
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목1',
`body` = '내용1',
memberId = 1,
boardId = 1;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목2',
`body` = '내용2',
memberId = 1,
boardId = 1;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목3',
`body` = '내용3',
memberId = 1,
boardId = 1;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목3',
`body` = '내용3',
memberId = 1,
boardId = 1;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목3',
`body` = '내용3',
memberId = 1,
boardId = 1;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목3',
`body` = '내용3',
memberId = 1,
boardId = 1;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목3',
`body` = '내용3',
memberId = 1,
boardId = 1;


# 회원 테이블 생성
CREATE TABLE `member` (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    loginId CHAR(30) NOT NULL,
    loginPw VARCHAR(50) NOT NULL,
    `name` CHAR(30) NOT NULL
);

# 회원 데이터 생성
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'test1',
loginPw = 'test1',
`name` = '테스터1';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'test2',
loginPw = 'test2',
`name` = '테스터2';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'admin',
loginPw = 'admin',
`name` = '김규범';

# 게시판 테이블 생성
CREATE TABLE board (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    `name` CHAR(20) NOT NULL,
    `code` CHAR(20) NOT NULL
);

# 공지사항 게시판 추가
INSERT INTO board 
SET regDate = NOW(),
updateDate = NOW(),
`name` = '공지사항',
`code` = 'notice';

# 자유 게시판 추가
INSERT INTO board 
SET regDate = NOW(),
updateDate = NOW(),
`name` = '자유',
`code` = 'free';

# IT 게시판 추가
INSERT INTO board 
SET regDate = NOW(),
updateDate = NOW(),
`name` = 'JAVA',
`code` = 'it';


UPDATE article
SET boardId = 2
LIMIT 2;

#게시물 랜덤 생성

SELECT * FROM article;

/*INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = CONCAT("제목_", RAND()),
`body` = CONCAT("내용_", RAND()),
memberId = FLOOR(RAND() * 2) + 1,
boardId = FLOOR(RAND() * 1) + 1;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = CONCAT("제목_", RAND()),
`body` = CONCAT("내용_", RAND()),
memberId = FLOOR(RAND() * 2) + 1,
boardId = FLOOR(RAND() * 1) + 1;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = CONCAT("제목_", RAND()),
`body` = CONCAT("내용_", RAND()),
memberId = FLOOR(RAND() * 2) + 1,
boardId = FLOOR(RAND() * 1) + 1;
*/

SELECT * FROM article;
SELECT * FROM board;



# 1번글 내용에 내용 넣기
UPDATE article SET title = '공지사항', `body` = '# 공지사항\r\n- 개발자 블로그\r\n```\r\n환영합니다\r\n```', memberId = 3, boardId = 1
WHERE `id` = '1'; 

# 2번글 내용에 내용 넣기
UPDATE article SET title = '자유1', `body` = '# 자유게시판\r\n\r\n```\r\n자유게시판입니다.\r\n```', memberId = 3, boardId = 2
WHERE `id` = '2';

# 3번글 내용에 내용 넣기
UPDATE article SET title = '자유2', `body` = '# 자유게시판\r\n- 개발자 블로그\r\n```\r\n환영합니다.\r\n```', memberId = 3, boardId = 2
WHERE `id` = '3'; 

# 4번글 내용에 내용 넣기
UPDATE article SET title = '1강. JAVA란?', `body` = '# 자바란 ?\r\n\r\n- 자바 소개\r\n```\r\n미국의 썬마이크로시스템즈(썬)에서 개발한 언어이다.\r\n2010년 오라클에서 썬을 인수하여 JAVA의 개발, 관리, 배포를 주관하고 있다.\r\n```\r\n\r\n- 자바 특징\r\n```\r\n1. 운영체제에 독립적\r\n2. 객제지향언어\r\n3. 배우기 쉬움\r\n4. 멀티쓰레드 지원\r\n5. 자동메모리 관리\r\n6. 네트워크와 분산처리 지원\r\n```\r\n'
,memberId = 3, boardId = 3
WHERE `id` = '4';

# 5번글 내용에 내용 넣기
UPDATE article SET `title` = '2강. JAVA / 변수' , `body` = '# 변수란\r\n```\r\n데이터를 저장하기 위해 프로그램에 의해 이름을 할당받은 메모리 공간을 의미한다.\r\n```\r\n\r\n# 변수의 이름 생성 규칙\r\n```\r\n1. 변수의 이름은 영문자(대소문자), 숫자, 언더바(_),달러($)로만 구성할 수 있다.\r\n2. 변수의 이름은 숫자로 시작할 수 없다.\r\n3. 변수의 이름 사이에는 공백을 포함할 수 없다.\r\n4. 변수의 이름으로 자바에서 미리 정의된 키워드는 사용할 수 없다\r\n```\r\n\r\n# 변수의 종류\r\n```\r\n1. 기본형 변수\r\n\r\n- 정수형 : byte, short, int, long\r\n- 실수형 : float, double\r\n- 문자형 : char\r\n- 논리형 : boolean\r\n\r\n2. 참조형 변수\r\n\r\n참조형 변수는 8개의 기본형 변수를 사용하여 사용자가 직접 만들어 사용하는 변수를 의미한다\r\n```\r\n\r\n# 변수의 선언\r\n```\r\n1. 변수의 선언만 하는 방법\r\n\r\n- 문법 : 타입 변수이름;\r\n\r\nint num; // 변수의 선언\r\nSystem.out.println(num); // 오류 발생\r\nnum = 20;\r\nSystem.out.println(num); // 20\r\n\r\n2. 변수의 선언과 동시에 초기화\r\n\r\n- 문법 : \r\n1. 타입 변수이름[변수이름];\r\n2. 타입 변수이름 = 초깃값[변수이름 = 초깃값];\r\n\r\nint num1, num2; // 같은 타입의 변수를 동시에 선언\r\ndouble num3 = 3.14; // 선언과 동시에 초기화\r\ndouble num4 = 1.23, num5 = 4.56 // 같은 타입의 변수를 동시에 선언하면서 초기화\r\n```\r\n\r\n\r\n\r\n# 잘못된 예 \r\n```\r\ndouble num1, num2; // 같은 타입의 변수를 동시에 선언\r\nnum1 = 1.23, num2 = 4.56; // 이미 선언된 여러 변수를 동시에 초기화할 수는 없다.\r\n```\r\n\r\n\r\n\r\n' 
,memberId = 3, boardId = 3
WHERE `id` = '5';

# 6번글 내용에 내용 넣기
UPDATE article SET `title` = '3강. JAVA / 상수' , `body` = '# 상수란\r\n```\r\n변수와 마찬가지로 데이터를 저장할 수 있는 메모리 공간을 의미한다.\r\n```\r\n\r\n# 상수\r\n```\r\n상수는 변수와 마찬가지로 이름을 가지고 있는 메모리 공간으로 이러한 상수는 선언과 동시에 초기화 해야한다\r\n\r\n- 상수를 만드는 일반적인 방식\r\n- final int AGES = 30;\r\n\r\n```\r\n\r\n# 리터럴\r\n```\r\n리터럴이란 그 자체로 값을 의미한다\r\n즉, 변수와 상수와는 달리 데이터가 저장된 메모리 공간을 가리키는 이름을 가지고 있지 않다.\r\n\r\nex )\r\n\r\nint var = 30; // 30이 리터럴이다\r\nfinal int AGES = 100; // 100이 리터럴이다.\r\n```\r\n'
,memberId = 3, boardId = 3
WHERE `id` = '6';

# 7번글 내용에 내용 넣기
UPDATE article SET `title` = '4강. JAVA / 기본 타입' , `body` = '# 기본 타입\r\n```\r\n타입은 해당 데이터가 메모리에 어떻게 저장되고, 프로그램에세 어떻게 처리되어야 하는지를 명시적으로 알려주는 역할을 한다.\r\n자바의 기본타입은 모두 8종류가 제공되며, 정수 실수 문자 그리고 논리형 타입으로 나눌 수 있다.\r\n```\r\n\r\n# 정수형 타입\r\n```\r\n- 자바에서 정수란 부호를 가지고 있으며, 소수 부분이 없는 수\r\n\r\n정수형 타입 / 할당 메모리 크기 / 데이터 표현 범위\r\n \r\n1. byte / 1바이트 / -128 ~ 127\r\n2. short / 2바이트 / -2(15) ~ (2(15) - 1)\r\n3. int / 4바이트 / -2(31) ~ (2(31) - 1)\r\n4. long / 8 바이트 / -2(63) ~ (2(63) - 1)\r\n\r\n정수형 데이터의 타입을 결정할 때에는 반드시 자신이 사용하고자 하는 데이터의 최대 크기를 고려해야한다.\r\n```\r\n\r\n# 실수형 타입\r\n```\r\n- 자바에서 실수란 소수부나 지수부가 없는 수를 가리키며, 정수보다 훨씬 더 넓은 표현범위를 가진다.\r\n\r\n실수형 타입 / 할당 메모리 크기 / 데이터 표현 범위\r\n\r\n1. float / 4바이트 / (3.4 X 10(-38)) ~ (3.4 X 10(38))\r\n2. double / 8바이트 / (1.7 X 10(-308) ~ (1.7 X 10(308))\r\n\r\n컴퓨터에서 실수를 표현하는 방식은 오차가 발생할 수 밖에 없는 태생적 한계를 지니고 있다.\r\n```\r\n\r\n# 문자형 타입\r\n```\r\n- 자바에서 문자형 데이터란 작은 정수나 문자 하나를 표현할 수 있는 타입을 의미한다.\r\n\r\n\r\n컴퓨터는 2진수밖에 인식하지 못하므로 문자도 숫자로 표현해야 인식할 수 있습니다.\r\n\r\n따라서 어떤 문자를 어떤 숫자에 대응시킬 것인가에 대한 약속이 필요해집니다. \r\n\r\nC언어와 C++에서는 아스키코드(ASCII)를 사용하여 문자를 표현합니다.\r\n\r\n아스키코드(ASCII)는 영문 대소문자를 사용하는 7비트의 문자 인코딩 방식입니다.\r\n\r\n아스키코드는 문자 하나를 7비트로 표현하므로, 총 128개의 문자를 표현할 수 있습니다. \r\n\r\n하지만 자바에서는 유니코드(unicode)를 사용하여 문자를 표현합니다.\r\n\r\n아스키코드는 영문자와 숫자밖에 표현 못 하지만, 유니코드는 각 나라의 모든 언어를 표현할 수 있습니다.\r\n\r\n유니코드는 문자 하나를 16비트로 표현하므로, 총 65,536개의 문자를 표현할 수 있습니다.\r\n\r\n\r\n\r\n문자형 타입 / 할당 메모리 크기 / 데이터 표현 범위\r\n\r\n1. char / 2바이트 / 0 ~ 2(16)\r\n\r\n```\r\n\r\n\r\n# 논리형 타입\r\n```\r\n- 논리형은 참(true)이나 거짓(false) 중 한 가지 값만을 가질 수 있는 불리언 타입을 의미한다.\r\n\r\nboolean형의 기본값은 false이며, 기본 타입 중 가장 작은 크기인 1바이트의 크기를 가집니다\r\n\r\n문자형 타입 / 할당 메모리 크기 / 데이터 표현 범위\r\n\r\n1. boolean / 1바이트 / true 또는 false\r\n```\r\n'
,memberId = 3, boardId = 3
WHERE `id` = '7';


SELECT * FROM article;

