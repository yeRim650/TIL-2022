### bean
* bean : 스프링 프레임워크에서 관리되는 자원
* Spring을 많이 사용하는 이유로 bean 개념을 들 수 있다
* Compment bean에 등록된다는 개념
  * Controller, Service, Repository 는 bean에 등록된다는 개념 외에도 내부적으로 다른 의미(기능)을 가지고 있다
    * 시멘틱 태그
* **스프링이 관리하는 bean은 기본적으로 싱글톤 형태로 관리된다**  
(객체가 한개이기 때문에 변경시 어플리케이션 상 다른 사용자에게 영향을 줄 수 있으므로 신중해야 한다)  
(어플리케이션 상 싱글톤으로  존재하는 객체는 값이 공유가 된다)
#### bean으로 등록하는 방법
* 클레스에 어노테이션을 추가
  * 제일 간편한 방법이지만 내가 만든 클래스거나 수정 권한이 있는 클래스인 경우에만 bean에 등록이 가능
* root_context.xml 설정 파일에 추가
  * 어노테이션을 클래스에 직접 추가못하는 경우 사용
### Spring을 사용하는 이유
* -어노테이션 기반(코드 작성시 제약이 적다)
* **DI (의존성 주입)으로 의존성을 낮춘다. (의존관계를 외부에서 결정하고 주입) **
  * (리플렉션과 유사하게 객체를 직접 생성하지 않아도 객체 생성을 알아서 해준다)
  * (Spring이 객체를 만들어 꺼내준다)
  * 객체 생성이나 관리에 대한 책임을 지지 않아도 Spring이 대신 해준다(의존성을 대한 관리)
### 의존성
* 클래스가 있기 위해서는 그 안에 맴버변수가 필요 -> 클래스는 맴버변수에 대해 의존성을 가지고 있다
* 맴버변수는 해당 클래스의 구성요소
* 지금까지 의존성을 가지는 대상에 대해서 코딩을하는 개발자가 객체 생성과 관리에 대한의 의무를 담당했지만 Spring이 대신해 줄 수 있다
* 맴버변수로 쓰이는 객체가 필요시 Spring에게 요청하여 받을 수 있다 (클래스를 bean으로 등록하고 필요할 때 스프링에게 요구할 수 있다)
### DI 의존관계 주입(Dependency Injection)동작원리 
* 멤버변수에 **@Autowired(자동 연결)**가 붙어 있으면 프로젝트 안에 등록된 bean들 중 해당 타입으로 사용할 수 있는 bean을 멤버변수 값으로 넣어 준다
* 타입이 인터페이스로 들어올 수 있는 객체가 여러개면 변수 이름으로 의도 파악
* bean을 식별하기 위해 모든 bean의 식별자로 id를 부여 클래스 이름에 첫 글자만 소문자로 바꾸어 지정
* **@Component("myZealot")** 아이디 임의로 지정
* @Autowired를 할때 **@Qualifier("mutal")** IUnit에 id가 mutal인 객체를 생성을 지정 (bean을 지정해 준다) 
  * 단, 알아서 자동으로 넣어 주는 것이 더 나은방법
* **따라서 @Autowired시 변수명과 id를 생각해 신경 써주어야 한다**
* 클래스 멤버변수 값을 **@Value**를 이용해 넣어 줄 수 있다
```java
package org.green.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyArmy {
	@Autowired
	private IUnit myZealot;
	@Autowired
	private IUnit mutal;
	@Override
	public String toString() {
		return "MyArmy [myZealot=" + myZealot + ", mutal=" + mutal + "]";
	}
}
```
### DI 사용 목적
* 의존성이 존재하는 객체 관계에서 기존에는 우리가 원하는 의존성을 가지는 대상을 직접 만들고 직접 관계 설정을 해줬던 것에 반에
* Spring에서는 객체생성도 직접하지 않아도 되고 의존성을 주입해주기 때문에 직접 대입하지 않아도 된다 Spring이 해준다.
* 인터 페이스를 만들어서 맴버변수에 들어가는 객체를 모르게 만들어 졌지만 의도대로 원하는 객체 대입이 가능하다 변경이 일어나도 다른 곳에서 영향을 받지 않는다
