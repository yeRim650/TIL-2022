## 컴퓨터 구조와 원리(기초)
> 컴퓨터 구조알고 효율적으로 코딩하자
### 트렌지스터
* 컴퓨터의 기본적 요소
* cpu는 트렌지스터 덩어리
* 트렌지스터 기능 : 스위치 (전기적 신호로 작동하는 스위치)

> <img src="https://postfiles.pstatic.net/MjAyMjA2MTRfMjg2/MDAxNjU1MTk1NzQzOTMx.ybW519IPFmcu2852Wf8mHtYJ3zcSxL6RN_c5yFZSPx8g.G2MBXfk9o8TGZ6TqEqcsgPC_-pwZWInp7zH97hRCm94g.PNG.forget980/image.png?type=w580" width="15%" height="5%" title="px(픽셀) 크기 설정" alt="RubberDuck"></img><npn 트렌지스터 기호>
* 트렌지스터가 0과 1을 표현 (1 : 전류가 흐른다 0 : 전류가 안 흐른다)
#### 2진법
* 0과 1로 모든것을 표현(2진수)
* 컴퓨터는 2진수로 이루어진다(현실에서는 10진법 많이 사용 10진수를 2진수를 변경하는 방법이 있다)

> ###### 0이나 1로 표현하는 1bit 트렌지스터 하나  
> ###### 1bit가 8개 모이면 1 byte 
> ###### 1byt 1024개 모이면 1Kbyte  

#### 트렌지스터로 계산기 만들기
* Boolean 대수 : And Or Not Xor
* not 게이트는 트렌지스터 하나로 만들어진다  
<img src="https://postfiles.pstatic.net/MjAyMjA2MTRfMTUx/MDAxNjU1MTk5NTI4OTIx.B5t57OvGUGH3lMGNjInJFp0hIwgGB0jeSzROKoyYWXwg.JZ28Ix5yKq3hFPC8PgmVPJ9vH3WxxyOJYBv9FqvDjfYg.PNG.forget980/image.png?type=w580" width="20%" height="10%" title="px(픽셀) 크기 설정" alt="RubberDuck"></img>(이미지 출처 : http://recipes.egloos.com/4971109)
* and 게이트는 트렌지스터 두개가 만들어진다
* 두개의 트렌지스터가 도체가 되야 전류가 흘러 1이 Output  
<img src="https://postfiles.pstatic.net/MjAyMjA2MTRfMTk1/MDAxNjU1MTk5NjA0MzU2.SWQU3AkYipAaLBb96-wyc8z12hOTQh30hmR_s6IXGG4g.nciPGUCiHmski4PYWyM8Ia47qCruR0D6e3nDNyFpRzog.PNG.forget980/image.png?type=w580" width="20%" height="10%" title="px(픽셀) 크기 설정" alt="RubberDuck"></img>(이미지 출처 : http://recipes.egloos.com/4971109)
> ##### 옴의 법칙 V = IR  I = V/R I:전류 V:전압 R:저항  
> ##### 전류는 저항에 반비례  
> ##### 도체 : 전류가 흐르는 물체 ex 구리,철,금,물(저항이 0에 가깝다 작다)  
> ##### 부도체 : 전류가 흐르지 않는 물체 ex 그릇, 세라믹, 돌 (저항이 무한대에 가깝다 크다)  
> ##### **반도체** :  베이스에 접압이 없으면 부도체 전압이 있으면 도체
* 1bit 가산기
  * Sum부분은 XOR Carry AND
  * 2진법 계산 0 + 1 -> s는 1 c는 0 따라서 01
  * Carry 올림이 없는 경우만 사용 가능  
  <img src="https://w.namu.la/s/d5b543d9c76b137e802c3bb655f041ab57fbe39c3d9b6f3fcd7495cab8ec3ea67b4b4bb114a4426bf582da42c51d4fc1618201ab47c39accb025b89313e6f41211ed92924bc1dc391ec09c7ebe2fee9fda6ff9b4ce8ab881256b8314046a8626" width="20%" height="10%" title="px(픽셀) 크기 설정" alt="RubberDuck"></img>
* 이진 가산기
  * 최하위 비트는 s출력이 그대로이지만 상위 비트는 이전 비트의 올림수를 더해주어야 한다.(이전 비트 올림수 필요)
* **트렌지스터로 논리소자를 만들고 논리소자로 가산기 감산기 곱셉 나눗셈이 가능해지면서 계산기가 된다**
### 초창기 컴퓨터
#### 튜링
* 튜링 테스트 : AI와 사람이 채팅했을때 사람에게 컴퓨터인지 사람인지 물어보고 사람이라고 말하면 통과
* 튜링 기계(가상) : 긴 테이프에 쓰여있는 여러 가지 기호들을 일정한 규칙에 따라 바꾸는 기계 (요청사항이 기계에 들어가면 수행한다)
#### 폰 노이만
* 프로그램 교체가 어렵다 -> 프로그램을 메모리에 넣고 ip메모리만 바꾸면 다른 프로그램을 실행
* 전자계산기,전자시계,네비게이션과 컴퓨터의 차이 컴퓨터는 프로그램 교체함으로써 여러 프로그램을 수행