## JWT-SAMPLE

### FLOW
```sequence
Title : JWT Process
Front->Backend: POST /auth/login with id, password
Backend->Backend: Create JWT
Backend->Front: Return JWT to Front
Front->Backend: Send API with JWT on the Authorization Header
Backend->Backend: JWT Check
Backend->Front: Send Response
```

### OAUTH와의 차이점
- OAuth는 특별한 정보를 가지지않는 일반적인 스트링형태이지만
  Claim기반의 토큰으로  Claim기반이라는 방식은 사용자에 대한 프로퍼티나
  속성을 정보로 가지는 방식으로 JWT는 이 방식을 사용하여 JSON형태로
  토큰을 만든다.