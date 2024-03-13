# 항해99 주특기 Lv.4 과제 -  강의 사이트 서버 만들기 REST API

## 📝 개요
- 강의 사이트 서버 만들기 RESTful API 개발 과제입니다.
- Spring Security + JWT 로그인 으로 인증 & 인가 로직을 비즈니스 로직과 분리
---

### 🛠️ Stack
- IntelliJ IDEA Ultimate
- Gradle
- Spring Security
- Spring boot v3.2.3
- Spring MVC
- Spring Data JPA
- MySQL
- Java 17

### ERD
![image](https://github.com/OKSUchun/spring4/assets/115769951/d9c844b6-96e5-4a3c-abab-2b00f2793805)



### Usecase Diagram
![image](https://github.com/OKSUchun/spring4/assets/115769951/95408233-30b5-4920-89cc-2c2dc34470ac)


### API 명세서 (Postman)
- Postman API Document 입니다.
- https://documenter.getpostman.com/view/33317396/2sA2xiWrme

---

## 💡주요 기능
- 회원(관리자)
  - [x] 회원 가입
  - [x] 회원 로그인
- 강사
  - [x] 강사 등록
  - [x] 선택한 강사 정보 수정 - ADMIN 권한만 가능
- 강의
  - [x] 강의 등록
  - [x] 선택한 강의 정보 조회
  - [x] 카테고리 강의 목록 조회
  - [x] 선택한 강의 댓글 작성 기능
  - [x] 선택한 강의의 선택한 댓글 수정 기능 - 작성자만 가능
  - [x] 선택한 강의의 선택한 댓글 삭제 기능 - 작성자만 가능
  - [x] 선택한 강의 좋아요/좋아요 취소 기능
