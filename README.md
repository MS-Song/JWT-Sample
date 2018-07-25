## JWT-SAMPLE

### FLOW
<img src="http://bboroccu.github.io/assets/img/jwt_flow.png" width="80%"/>

### OAUTH와의 차이점
- OAuth는 특별한 정보를 가지지않는 일반적인 스트링형태이지만
  Claim기반의 토큰으로  Claim기반이라는 방식은 사용자에 대한 프로퍼티나
  속성을 정보로 가지는 방식으로 JWT는 이 방식을 사용하여 JSON형태로
  토큰을 만든다.


### 소스코드 실행시
- java 9 에서는 실행시 option 에 --add-modules java.xml.bind  추가
