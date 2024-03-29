### 클라이언드와의 대화 1 : 쿠키
* 웹서버는 쿠키를 이용해서 웹 브라우저에 정보를 전송할 수 있다.
* 웹 서버로부터 쿠키를 전달받은 웹 브라우저는 이후 웹 서버에 요청을 보낼 때 쿠키를 함꼐 전송
* 이를 사용하면 웹 서버와 웹 브라우저는 필요한 값을 공유하고 상태를 유지할 수 있다.
* 쿠키를 사용하는 이유는 로그인 상태와 같이 클라이언트 상태를 웹 브라우저에 저장하는데 있으며 서버측 부하를 줄여주는 효과가 있다
* 예를 들어 로그인 이후 로그인 상태임을 쿠키로 심어 둔다
* 단, 브라우저의 설정으로 쿠키 이용이 불가능할 수 있어 무조건 동작을 보장해 주지 않는다.
#### 쿠키 사용하기
* 쿠키는 웹 브라우저가 보관하는 데이터
* 웹 브라우저는 웹 서버에 요청을 보낼 때 쿠키를 함꼐 전송
* 웹 서버는 웹 브라우저가 전송한 쿠키를 사용해서 필요한 테이터를 읽을 수 있다
* 쿠키는 웹 서버와 웹 브라우저 양쪽에서 생성할 수 있는데, JSP에서 생성하는 쿠키는 웹 서버에서 생성하는 쿠키이다.
* 쿠키 동작 방식
  * 1. 쿠키 생성 단계 
    * 웹 서버 측에서 생성 
    * 생성한 쿠키를 응답 데이터의 헤더에 저장해서 웹 브라우저에 전송
  * 2. 쿠키 저장 단계
    * 포함된 쿠키 저장소에 보관
    * 메모리나 파일에 저장
  * 3. 쿠키 전송 단계
    * 웹 브라우저는 저장한 쿠키를 요청이 있을 때마다 웹 서버에 전송(요청 헤더에 쿠키가 담긴다)
    * 웹 서버는 웹 브라우저가 전송한 쿠키를 사용해서 필요한 작업을 수행
* 쿠키의 구성(핵심요소 이름, 값)
  * 이름 (쿠키 이름 하나당 값 하나 (이름: 값), 쿠키는 복수 데이터가 아니다)
    * 콤마, 세미콜론, 공배그 등호기호('=')를 제외한 출력 가능한 아스키 문자로 구성
  * 값
  * 유효시간
  * 도메인
  * 경로
* **하나의 웹 브라우저는 여러 개의 쿠키를 가질 수 있는데, 각 쿠키를 구분할 때 이름을 사용**
* 각 쿠키는 값을 가지며 서버는 이 값을 사용해서 원하는 작업을 수행
* 유효시간을 사용하면 웹 브라우저가 쿠키를 얼마 동안 보관할지를 지정할 수 있다.
* **별도 유효 시간을 지정하지 않으면 웹 브라우저를 종료할 때 쿠키를 함계 삭제**
* 지정한 도메인이나 경로만 쿠키를 전송하도록 제한
#### 쿠키 생성하기
* 처음 플러시 전까지만 헤더 변경이 가능하므로 그전에 쿠키를 응답헤더에 담겨 있어야 한다 (이후에는 헤더에 추가 변경 불가)
* 쿠키의 값은 임의 값이 아닌 서버에서 지정한 값이므로 한글은 피한다
* Cookie 클래스가 제공하는 메서드
  * getName()
  * getValue()
  * setValue(String value)
  * setDomain()
  * setPath(String uri)
  * getPath()
  * setMaxAge(int expiry) - 유효시간을 초 단위로 지정 (음수를 입력할 경우 웹 브라우저를 닫을 때 쿠키가 함꼐 삭제, 유효시간을 지정하지 않으면 음수가 기본값)
  * getMaxAge()
#### 쿠키 값 읽어오기
* Cookie[] cookies = request.getCookies();
* request객체 안에 쿠키가 여러개 일수 있기 때문에 쿠키 배열로 리턴
```jsp
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	Cookie cookie = new Cookie("myName", "myValue");
	response.addCookie(cookie);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>쿠키생성</title>
</head>
<body>

<%= cookie.getName() %> 쿠키의 값 = "<%= cookie.getValue() %>"

</body>
</html>
```
```jsp
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>쿠키 목록</title>
</head>
<body>
쿠키 목록<br>
<%
	Cookie[] cookies = request.getCookies();
	if(cookies != null && cookies.length>0){
		for(int i = 0; i < cookies.length;i++){
%>
	<%= cookies[i].getName() %> =
	<%= cookies[i].getValue() %><br>			
<%
		}
	}else{
%>
쿠키가 존재하지 않습니다.
<%
	}
%>
</body>
</html>
```
#### 쿠키 사용하는 예시
* Redirect를 사용하는 경우 url을 노출되기 때문에 일정한 과정을 거쳐 페이지에 도달하는 것이 아닌 url을 통해 페이지에 도달할 가능성이 있다
* 이러한 과정없이 페이지에 도달하는 것을 막기 위해 쿠키를 통해 확인 후 결과 페이지를 보여줄 수 있다
* 쿠키는 사용자가 의도한대로 과정을 거쳐서 왔는지 아니면 안 거치고 왔는지 **클라이언트 상태**를 확인하는 것에 쿠키를 이용
* 메일함을 이용할 때 로그인이 되어 있는지 확인하기 위해 웹 브라우저에 로그인 상태로 쿠키가 심어져 있는지 확인하면 된다
* test.jsp (Redirect시 이동하는 페이지에서 쿠키를 확인한 예시)
```jsp
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>test.jsp</title>
</head>
<body>
	<%
		String msg = request.getParameter("msg");
		if(msg == null){
			msg = "wellcome~~!!";
		}
	%>
	<h1><%= msg %></h1>
	<hr>
	<form action="process.jsp" method="post">
		당신의 혈액형은?
		<br>
		<input type="radio" name="bloodType" value="A"/>A
		<input type="radio" name="bloodType" value="B"/>B
		<input type="radio" name="bloodType" value="O"/>O
		<input type="radio" name="bloodType" value="AB"/>AB
		<br>
		<input type="submit"/>
	</form>
</body>
</html>
```
* process.jsp
```jsp
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	String bloodType = request.getParameter("bloodType");
	//특정연산한다...
	Cookie c = new Cookie("bloodType", bloodType);
	response.addCookie(c);
	
	response.sendRedirect("result.jsp");
%>
```
* result.jsp
```jsp
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>result.jsp</title>
</head>
<body>
	<h1>테스트 완료~</h1>
	<hr>
	<%
		boolean checked = false;
		Cookie[] arr = request.getCookies();
		if(arr != null){
			for(Cookie temp : arr){
				if(temp.getName().equals("bloodType")){
					out.println("당신의 혈액형 : " + temp.getValue());
					checked = true;
				}
			}
		}
		if(!checked){
		//쿠키가 존재하지 않는 경우 forword로 페이지 이동
	%>
	<jsp:forward page = "test.jsp">
		<jsp:param value="access denied" name="msg"/>
	</jsp:forward>
	<%
		}
	%>
</body>
</html>
```
##### 쿠키의 한계
* 서버가 브라우저 리소스를 사용해야 하는데 브라우저에서 쿠키를 허용안하면 서버에서 쿠키를 아무리 보내도 저장 안된다
* 쿠기 기술은 보조수단이다(클라이언트 상태를 유지하는 확실한 방법이 아니다, 쿠키만을 사용해서 상태를 저장하는 것은 좋은 방법이 아니다)
#### 쿠키 값 변경 및 쿠키 삭제하기
* 쿠키 값 변경 및 쿠키를 삭제하는 메서드는 없다
* 기존의 쿠키 이름과 동일한 이름으로 변경하고자 하는 값으로 쿠키를 만들어 보내면 기존 쿠키를 덮어쓰면서 변경되는 효과를 낼 수 있다
* 서버측에 관리하는 경우 바꾸는 메서드가 존재할 수 있지만 저장된 장소가 클라이언트이기 때문에 새로 쿠키를 만들어 덮어쓰는 방법을 사용
* 서버측에서 가지고 있는게 아닌 클라이언트 측에 있기 때문에 서버측 컴퓨터가 아니기 때문에 마음대로 변경하고 삭제 할 수 없어 직접적인 메서드가 존재하지 않는다.
* 쿠키의 유효시간을 0으로 주어 쿠키를 삭제할 수 있다(지우는 메서드가 없다)
* cookie.setMaxAge(0);
* (서버가 할일을 클라이언트에게 시키는 좀비 PC)
#### 쿠키의 도메인
* 기본적으로 쿠키는 그 쿠키를 생성한 서버에만 전송
* 같은 도메인을 사용한 모든 서버에 쿠키를 보내야 할 때가 있다
* .somehost.com : 점으로 시작하는 경우 관련 도메인에 모두 쿠키를 전송
* www.somhost.com : (영어로 시작하는 경우)**특정 도메인**에 대해서만 쿠키를 전송
* 쿠키 1 : .somehost.com을 도메인으로 지정
* 쿠키 2 : 도메인 지정 안함 = javacan.somehost.com으로 설정된다(쿠키 2를 만든 도메인으로 설정)
* 쿠키 3 : javacan.tistory.com을 도메인으로 지정
* javacan.somehost.com에서 수행하는 경우
	* 쿠키 3은 웹 브라우저에서 쿠키 저장 x
	* 쿠키 2는 지정한 도메인과 완전히 일치 하므로 저장 o
	* 쿠키 1은 .somehost.com 관련 도메인이므로 저장 o
#### 쿠키의 경로
* 도메인이 쿠키를 공유할 도메인 범위를 지정한다면, 경로는 쿠키를 공유할 기준 경로를 지정
* /chap09로 경로를 지정하는 경우 /chap09 또는 그 하위 경로에만 전송
* path 1 : /chap09/path1
* path 2 : 지정하지 않음(쿠키에 경로를 지정하지 않으면, 실행한 URL의 경로 부분을 사용)
* path 3 : / (도메인 밑의 모든 path를 다 포함 한다)
* path 4 : /chap09/path2
* 일반적으로 쿠키는 웹 어플리케이션에 포함된 다수의 JSP와 서블릿에서 사용하기 때문에, 쿠키 경로를 "/"로 지정
#### 쿠키의 유효시간
* 쿠키를 한시간으로 지정시
	* cookie.setMaxAge(60*60) // 60초(1분) * 60분 = 1시간
* 로그인 정보를 쿠키에 보관하면 자동 로그인 기능 구현
#### 쿠키와 헤더
* 쿠키이름=쿠키값; Domain=도메인값; path=경로값; Expires=GMT형식의만료일시
* 쿠키는 출력 버퍼를 플러시하기 전에 추가해야 한다
#### 쿠키를 이용한 로그인 상태 유지
1. 로그인에 성공하면 특정 이름을 갖는 쿠키를 생성
2. 해당 쿠키가 존재하면 로그인한 상태라고 판단
3. 로그아웃하면 해당 쿠키를 삭제
```java
package kr.ac.green;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class Cookies {
	private Map<String, Cookie> cookieMap = new java.util.HashMap<String, Cookie>();

	public Cookies(HttpServletRequest reqest) {
		Cookie[] cookies = reqest.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookieMap.put(cookies[i].getName(), cookies[i]);
			}
		}
	}

	public Cookie getCookie(String name) {
		return cookieMap.get(name);
	}

	public String getValue(String name) throws IOException {
		Cookie cookie = cookieMap.get(name);
		if (cookie == null) {
			return null;
		}
		return URLDecoder.decode(cookie.getValue(), "euc-kr");
	}

	public boolean exits(String name) {
		return cookieMap.get(name) != null;
	}

	public static Cookie createCookie(String name, String value) throws IOException {
		return new Cookie(name, URLEncoder.encode(value, "utf-8"));
	}

	public static Cookie createCookie(String name, String value, String path, int maxAge) throws IOException {
		Cookie cookie = new Cookie(name, URLEncoder.encode(value, "utf-8"));
		cookie.setPath(path);
		cookie.setMaxAge(maxAge);
		return cookie;
	}

	public static Cookie createCookie(String name, String value, String domain, String path, int maxAge)
			throws IOException {
		Cookie cookie = new Cookie(name, URLEncoder.encode(value, "utf-8"));
		cookie.setDomain(domain);
		cookie.setPath(path);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
}
```
* loginForm.jsp
```jsp
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>로그인폼</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/login.jsp"
	method="post">
아이디 <input type="text" name="id" size="10">
암호 <input type="password" name="password" size="10">
<input type="submit" value="로그인">
</form>
</body>
</html>
```
* login.jsp
```jsp
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "kr.ac.green.Cookies" %>
<%
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	
	if(id.equals(password)){
		//ID와 암호가 같으면 로그인에 성공한 것으로 판다.
		response.addCookie(
			Cookies.createCookie("AUTH", id, "/", -1)	
		);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
로그인에 성공했습니다
</body>
</html>
<%
	}else{//로그인 실패시
%>
<script>
alert("로그인에 실패하였습니다.");
history.go(-1);
</script>
<%
	}
%>
```
* &lt;script&gt; 태그 안에는 자바스크립트 언어가 들어간다
* alert("로그인에 실패하였습니다."); 메시지 알림을 띄운다
* history.go(-1); 브라우저 방문기록에서 뒤로가기(이전 페이지로 돌아간다)
* loginCheck.jsp
```jsp
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "kr.ac.green.Cookies" %>
<%
	Cookies cookies = new Cookies(request);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>로그인여부 검사</title>
</head>
<body>
<%
	if(cookies.exists("AUTH")){
%>
아이디 "<%= cookies.getValue("AUTH") %>"로 로그인 한 상태
<%
	}else{
%>
로그인하지 않은 상태
<%
	}
%>
</body>
</html>
```
*logout.jsp
```jsp
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "kr.ac.green.Cookies"%>
<%
	response.addCookie(
		Cookies.createCookie("AUTH", "","/",0)//유효시간 0으로 (쿠키를 삭제하는 메서드는 없다)
	);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>로그아웃</title>
</head>
<body>

로그아웃하였습니다.

</body>
</html>
```
