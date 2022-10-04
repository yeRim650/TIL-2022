### 용어 정리
* 배치작업 : 데이터를 실시간으로 처리하는게 아니라, 일괄적으로 모아서 처리하는 작업을 의미
* mybatis : 객체 지향 언어인 자바의 관계형 데이터베이스 프로그래밍을 좀 더 쉽게 할 수 있게 도와 주는 개발 프레임 워크로서 JDBC를 통해 데이터베이스에 엑세스하는 작업을 캡슐화하고 일반 SQL 쿼리, 저장 프로 시저 및 고급 매핑을 지원하며 모든 JDBC 코드 및 매개 변수의 중복작업을 제거 합니다.
* CI/CD :지속적 통합/지속적 제공
* 리눅스서버
* shell(bash) 프로그램 : 서버를 배포하는(start, stop, git pull, build등 수행) 프로그램
* 젠킨스 : 자동배포 확경 구측
* aws + tomcat 아파치 톰캣
* HTML 메소드 GET POST PUT DELETE
* 크롬 개발자도구 : 웹 페이지 디버깅
* WireShark 프로그램 : Network 데이터에 Packet을 확인
* 단일 책임 원칙 (SRP : Single Responsibilty Principle)
* API (애플리케이션 프로그램 인터페이스) : 정의 및 프로토콜 집합을 사용하여 두 소프트웨어 구성 요소가 서로 통신할 수 있게 하는 메커니즘입니다.
  * 예를 들어, 기상청의 소프트웨어 시스템에는 일일 기상 데이터가 들어 있습니다. 휴대폰의 날씨 앱은 API를 통해 이 시스템과 "대화"하고 휴대폰에 매일 최신 날씨 정보를 표시
* 싱글톤(Singleton) : 객체의 인스턴스가 오직 1개만 생성
  * 싱글톤을 사용하는 이유 : 메모리 절략
  * 싱글톤을 사용시 문제점으로 trade-off를 고려
   * 첫번째 문제점 : 코드 자체가 많이 필요 멀티스레딩 환경에서 발생할 수 있는 동시성 문제 해결을 위한 syncronized 키워드를 사용
   * 두번째 문제점 : 테스트 어려움
   * 이외에도 자식클래스를 만들수 없다는 점과, 내부 상태를 변경하기 어렵다.
* 스프링 컨테이너 같은 프레임워크의 도움을 받으면 싱글톤 패턴의 문제점들을 보완하면서 장점의 해택을 누릴 수 있다. 실제 스프링 빈은 컨테이너의 도움을 받아 싱글톤 스콥으로 관리
* SSR, CSR
 * SSR(서버 사이드 렌더링) : HTML 최종 결과를 서버에서 만들어서 웹 브라우저에 전달
 * CSR(클라이언트 사이드 렌더링) : 주로 동적인 화면에서 사용, 웹 환경을 마치 앱 처럼 필요한 부분부분 변경할 수 있음
--------
* 카드 전산화 과정
* 전표 매입, 청구, 여신
  * 전표 = 영수증 정확히는 카드 결제에 의해 발생하는 영수증으로 거래 스인 번호, 가맹점 이름 등이 기입
  * 소비자뿐만 아니라 가맹점주도 거래 시 발생한 위와 같은 영수증 즉 전표를 챙긴다. 여러가지 이유가 있겠지만 대표적인 건 역시 카드사에게 **매입**하기 위함
  * 매입이란? 쉽게 말해 가맹점에게 모아둔 전표를 카드사를 제출
  * (카드 결제 과정엣 승인/매입 단계를 중개해주는 역할)
  * 청구란? 채권자가 채무자에 대하여 시효의 대상인 권리를 행사하는 것을 말합니다.
  * 여신이란? '신용'을 준다는 의미(금융기관일 경우에만 여신이라는 표현을 사용)