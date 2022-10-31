## OAuth
* **OAuth**는 인터넷 사용자들이 비밀번호를 제공하지 않고 다른 웹사이트 상의 자신들의 정보에 대한 웹 사이트나 애플리케이션의 접근 권한을 부여할 수 있는 공통적인 수단으로서 사용되는, 접근 위임을 위한 개방형 표준
### OAuth의 배경
> 간단하게 인증과 권한을 회득
* third party Application에 아이디와 비밀번호를 제공하고 싶지 않은 요구
* 개인정보를 여러곳에 입력하면 피싱에 둔감해지고 Application이 안전하다는 보장이 없기 때문에 보안에 취약
### Oauth 1.0 용어
![image](https://user-images.githubusercontent.com/102463200/198908308-c62e06d5-22e4-49a0-bc98-d68c798c10b8.png)
> Oauth 1.0 참여자  
* **Service provider** : OAuth를 사용하는 Open API를 제공하는 서비스
* **User** : Service Provider에 계정을 가지고 있으면서, Consumer를 이용하려는 사용자
* **Consumer** : OAuth 인증을 사용해 Service Provider의 기능을 사용하려는 애플리케이션이나 웹 서비스
```
* Request Token : Consumer가 Service Provider에게 접근 권한을 인증받기 위해 사용하는 값.   
  (인증이 완료된 후에는 Access Token으로 교환)
* Access Token : 인증 후 Consumer가 Service Provider의 자원에 접근하기 위해 키를 포함한 값.
```
### OAuth 1.0 인증 과정
![image](https://user-images.githubusercontent.com/102463200/198912658-2e563274-2d5c-4f5d-aebd-9c9bd9cadea4.png)

> **Request Token**의 요청과 발급 → 사용자 인증 페이지 호출 → 사용자 로그인 완료 → 사용자의 권한 요청 및 수락  
> → **Access Token** 발급 → **Access Token**을 이용해 서비스 정보 요청
* **Access Token을 가지고 있는 Consumer는 사전에 호출이 허락된 Service Provider의 오픈 API를 호출**
### oauth_signature 만들기
> Request Token 발급 요청시 발급 되는 oauth_signature 매개변수
* HMAC 암호화 방법을 이용하여 oauth_signature를 생성되는데 OAuth 1.0에서는 oauth_signature를 생성하는 것이 가장 까다롭다
## OAuth 2.0
> OAuth 1.0 단점  
> 웹 애플리케이션이 아닌 애플리케이션에서는 사용이 곤란하다  
> 절차가 복잡하여 OAuth 구현 라이브러리 제작이 어렵다  
> 복잡한 절차로 Service Provider에게도 연산 부담 발생  

![image](https://user-images.githubusercontent.com/102463200/198911779-2fec867e-1117-40cd-9331-5209484aac1c.png)
### OAuth 2.0 특징
* 웹어플리케이션이 아닌 애플리케이션 지원 강화
* 암호하가 필요 없음 (HTTPS를 사용하고 HMAC을 사용하지 않는다)
* Siganature 단순하다 (정렬과 URL 인코딩이 필요 없다)
* OAuth 2.0에서는 보안 강화를 위해 Access Token의 Life-time을 지정할 수 있다.
* 기능 단순화, 기능과 규모의 확정성 등을 지원하기 위해 만들어 짐
* 1.0a는 만들어진 다음 표준아 된 반면 2.0은 처음부터 표준 프로세스로 만들어짐
* 1.0a는 인증방식이 한가지 였지만 2는 다양한 인증방식을 지원
* api 서버에서 인증서버를 분리 할 수 있도록 해 놓아다
### Oauth 2.0 용어
![image](https://user-images.githubusercontent.com/102463200/198912837-168d58e5-c504-45f2-9f67-ea37a3a151a6.png)

> Oauth 2.0 참여자
* Resurce Server : OAuth2.0 서비스를 제공하고, 자원을 관리하는 서버 (보통 gogle, naver 같은 다른 사이트)
* Resurce Owner : 자원 소유 Resource Server의 계정을 소유하고 있는 사용자
* Client : Resource Server의 API를 사용하여 데이터를 가져오려고 하는 사이트 (개발 사이트)
* Authorization Server : Client가 Resource Server의 서비스를 사용할 수 있게 인증하고 토큰을 발생해주는 서버
```
* Access Token : 자원 서버에 자원을 요청할 수 있는 토큰
* Refresh Token : 권한 서버에 접근 토큰을 요청할 수 있는 토큰
```
### 인증 절차 종류
* Autorization Code Grant 
  *  Client가 다른 사용자 대신 특정 리소스에 접근을 요청할 때 사용
  *  리소스 접근을 위해, Authorization Server에서 받은 권한 코드로 리소스에 대한 액세스 토큰을 받는 방식
  *  다른 인증 절차에 비해 보안성이 높기 때문에 주로 사용
* Implicit Grant
  * Authorization Code Grant과 다르게 권한 코드 교환 단계가 있음
  * 엑세스 토큰을 즉시 반환받아 이를 인증에 사용하는 방식
* Resurce Owner Passward Credentials Grant
  * Client 암호를 사용하여 액세스 토큰에 대한 사용자의 자격 증명을 교화하는 방식
  * Resurce Owner에서 ID, Password를 전달 받아 Resurce Server에 인증하는 방식으로 신뢰할 수 있는 Client인 경우 가능
* Client Credentials Grant
  * Client가 컨텍스트 외부에서 엑세스 토큰을 얻어 특정 리소스에 접근을 요청할 때 사용하는 방식 
### OAuth 2.0 인증 과정
> Autorization Code Grant  

![image](https://user-images.githubusercontent.com/102463200/198913234-cdf8f073-d2c8-4248-9549-9a6d28e4cc9a.png)