#운영시작

# IT 게시판 추가
INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`name` = 'SQL',
`code` = 'it';

# 사용자 전부삭제
TRUNCATE `member`;

# 사용자 추가
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'admin',
loginPw = 'admin',
`name` = '김규범';

# 글 전부삭제
TRUNCATE article;

SELECT * FROM article;