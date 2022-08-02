작성 날짜 기준으로 내림차순 정렬하기
OrderBy 정렬, Desc 내림차순 (최신순)
domain : 테이블, 레포지토리,Timestamped,DTO
Service : 업데이트 관련 메소드
controller : API

API

전체 게시글 목록 조회 API//
Method: GET
제목,작성자명,작성날짜 조회(작성날짜는 내림차순 정렬)oo

게시글 작성 API//
Method: POST
제목, 작성자명, 비밀번호, 작성내용 oo

게시글 조회API
Method : GET/{id}
제목, 작성자명, 작성날짜, 작성 내용 조회oo

게시글 비밀번호 확인 API
Method: POST{id}
비밀번호를 입력받아 해당 게시글의 비밀번호와 일치여부 판단oo

게시글 수정 API
Method: PUT{id}
제목,작성자명,비밀번호,작성내용 수정o

게시글 삭제 API
Method : DELETE/{id}
글삭o





