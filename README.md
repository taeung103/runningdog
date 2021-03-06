## 지금달려갈개
#### 구현 기간 : 20/09/08 ~ 20/10/03
   - 유기동물 정보공유와 자원봉사모집, 후원, 동물 관련 이슈 공유를 목적으로 자바 스프링 프레임워크를 사용하여 반응형 웹 사이트로 제작한 팀 프로젝트입니다.

## 구현 기능 및 상세기능 설명
<사용자>
1. 로그인
    - 로그인 제한 여부
    - 일반로그인
    - 소셜 로그인(카카오, 네이버, 페이스북)
2. 회원가입
     - 간편화를 위해 회원가입 버튼 클릭과 동시에 중복검사(아이디(이메일), 닉네임, 핸드폰번호) 실행
     - 스프링 시큐리티를 사용해여 비밀번호 암호화처리
     - 소셜 로그인(카카오, 네이버, 페이스북) 으로 가입할 때 입력 폼이 각 소셜마다 다름. DB에 어떤 방식으로 회원가입 했는지 분류
3. 아이디 / 비밀번호 찾기
     - 비밀번호 찾기를 하면 가입한 이메일로 임시비밀번호가 전송되며 전송된 임시비밀번호가 DB에 자동으로 업데이트
4. 마이페이지(나의 프로필 수정)
     - 나의 프로필 정보 변경 가능(사진, 계정정보 등)
     - 회원탈퇴 가능
     - 소셜회원은 항목이 다름

<관리자>   
5. 전체회원 리스트 / 회원정보 수정 / 회원 등록 / 회원 선택탈퇴 Ajax    
6. 탈퇴회원 리스트 / 회원정보 상세 / 회원DB 선택삭제 Ajax  
7. 관리자 정보변경     
8. 회원 로그인 제한설정 / 관리자 권한설정  : 최고관리자만 설정 가능

<공통>        
9. 리스트 키워드 검색       
10. 계정정보 중복 검사      
11. 계정정보 정규표현식 적용       
12. 리스트 페이징
## 경로
  - [runningdog/src/main/java/com/kh/runningdog/member/](https://github.com/taeung103/runningdog/tree/master/src/main/java/com/kh/runningdog/member)
  - [runningdog/src/main/java/com/kh/runningdog/admin/member/controller/](https://github.com/taeung103/runningdog/tree/master/src/main/java/com/kh/runningdog/admin/member/controller)
  - [runningdog/src/main/webapp/WEB-INF/views/member/](https://github.com/taeung103/runningdog/tree/master/src/main/webapp/WEB-INF/views/member)
  - [runningdog/src/main/webapp/WEB-INF/views/admin/member/](https://github.com/taeung103/runningdog/tree/master/src/main/webapp/WEB-INF/views/admin/member)

## 🛠 개발환경      
💻 Java | JSP | Spring 3.9.13       
🌐 HTML | CSS | JavaScript | jQery 3.5.1        
🛢 Oracle Database 11g | SQL        
🔧 Git | SourceTree         
📫 apache-tomcat-8.5.57 
