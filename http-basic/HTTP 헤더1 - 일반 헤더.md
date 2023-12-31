## 🌏 HTTP 헤더

### 🌐 용도

* HTTP 전송에 필요한 모든 부가정보
* 표준 헤더가 진짜 많음
* 임의의 헤더 추가 가능

---

### 🌐 분류 - RFC2616 (과거)

* 헤더 분류
    * General 헤더 : 메세지 전체에 적용되는 정보 (Connection)
    * Request 헤더 : 요청 정보 (User-Agent)
    * Response 헤더 : 응답 정보 (Server)
    * Entity 헤더 : 엔티티 바디 정보 (Content-Type)
        - 엔티티 헤더는 엔티티 본문의 데이터를 해석할 수 있는 정보를 제공한다. 예를 들면 데이터 유형, 데이터 길이, 압축 정보 등등

🔥 그런데 2014년에 RFC7230 등장을 시작으로 7235까지 등장한다. (RFC2616은 폐기가 됨) 🔥

* Entity (엔티티) ▶️ Representation (표현) 로 바뀌고
* 표현 헤더는 엔티티 헤더와 같이 표현 데이터를 해석할 수 있는 정보를 제공한다.

---

### 🌐 표현

#### 🌳 **표현 헤더는 전송, 응답에 모두 사용**

* Content-Type : 표현 데이터의 형식   
  `text/html, application/json, image/png`


* Content-Encoding : 표현 데이터의 압축 방식
    * 표현 데이터 압축을 위해서 사용한다.
    * 데이터를 전달하는 곳에서 압축 후에 인코딩 헤더를 추가한다.
    * 데이터를 읽는 쪽에서는 인코딩 헤더의 정보로 압축을 해제한다.


* Content-Language : 표현 데이터의 자연 언어   
  `ko, en, en-US`


* Content-Length : 표현 데이터의 길이
    * 바이트 단위
    * Transfer-Encoding(전송 코딩을) 사용하면 Content-Length는 쓰면 안된다.

---

### 🌐 협상 (콘텐츠 네고시에이션)

> 클라이언트가 선호하는 표현 요청

#### 🌳 **협상 헤더는 요청시에만 사용**

* Accept : 클라이언트가 선호하는 미디어 타입 전달
* Accept-Charset : 클라이언트가 선호하는 문자 인코딩
* Accept-Encoding : 클라이언트가 선호하는 압축 인코딩
* Accept-Language : 클라이언트가 선호하는 자연언어


* 예를 들어서 한국어 브라우저를 사용할 때 Accept-Language를 같이 넘기면  
  응답할 때 한국어 언어지원이 있다면 한국어로 표현하는 것이다.

###                                        * 협상과 우선순위1 Quality Vales(q)

> `GET /event
> Accept-Language: ko-KR,ko:q=0.9,en-US;q=0.8,en;q=0.7`
>> 1순위 ko-KR; / 2순위 ko;q=0.9 / 3순위 en-US;q=0.8 / 4순위 en;q=0.7


⭐️ 0~1까지의 값으로 우선순위를 지정한다. 클 수록 높은 우선순위를 가진다. 생략하면 1

###                                        * 협상과 우선순위2 Quality Vales(q)

* 구체적인 것이 우선순위가 높다.

> `Accept: text/*, text/plain, text/plain;format=flowed, */*`
>> 1순위 text/plain;format=flowed / 2순위 text/plain / 3순위 text/* / 4순위 * / *

###                                        * 협상과 우선순위3 Quality Vales(q)

* 구체적인 것을 기준으로 미디어 타입을 맞춘다.

---

### 🌐 전송 방식

* 단순 전송  
  Cotent-Length를 알 수 있을 때 사용한다.


* 압축 전송  
  Content-Encoding을 함께 쓴다.


* 분할 전송  
  Transfer-Encoding: chunked 와 함께 쓴다.  
  분할 전송시에는 Content-Length를 보내면 안된다.


* 범위 전송
  요청시에는 Range로 범위를 지정하고   
  응답시에는 Content-Range로 범위를 지정해서 요청한다.

---

### 🌐 일반 정보

* From : 유저 에이전트의 이메일 정보
    * 일반적으로 잘 사용되지 않는다.
    * 검색 엔진 같은 곳에서 주로 사용
    * 요청에서 사용


* Referer : 이전 웹페이지 주소
    * A -> B로 이동하는 경우 B를 요청할 때 Referer: A를 함께 넣어서 요청한다.
    * Referer를 사용하면 유입 경로를 분석 가능하다.
    * referer는 단어 referrer의 오타이다.
    * 요청에서 사용


* User-Agent : 유저 에이전트 애플리케이션 정보
    * `Useragent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36`
    * 클라이언트의 애플리케이션 정보 (웹 브라우저 정보 등등)
    * 통계 정보
    * 어떤 종류의 브라우저에서 장애가 발생하는지 파악 가능
    * 요청에서 사용


* Server : 요청을 처리하는 오리진 서버의 소프트웨어 정보
    * `Server: Apache/2.2.22(Debian)`
    * `Server: nginx`
    * 응답에서 사용


* Date : 메세지가 생성된 날짜
    * `Date: Tue, 15 Nov 1994 08:12:31 GMT`
    * 응답에서 사용

---

### 🌐 특별한 정보

* Host : 요청한 호스트 정보 (도메인)
    * ⭐️필수
    * 하나의 서버가 여러 도메인을 처리해야 할 때
    * 하나의 IP 주소에 여러 도메인이 적용되어 있을 때
    * 요청에서 사용
    * Host 헤더를 넣어서 전달하면 서버에서 요청을 받았을 때 내부의 해당 도메인에 맞는 곳으로 요청한다.


* Location : 페이지 리다이렉션
    * 웹브라우저는 3XX 응답 결과에 Location 헤더가 있으면 Location 위치로 자동 이동
    * 201 (Created)에서 Location을 쓴다면 요청에 의해 자동으로 만들어진 URI라는 뜻


* Allow : 허용 가능한 HTTP 메서드
    * 405 (Method Not Allowed)에서 응답에 포함해야 함
    * Allow : GET, HEAD, PUT


* Retry-After : 유저 에이전트가 다음 요청을 기다려야 하는 시간
    * 503 (Service Unavailable) : 서비스가 언제까지 불가능한 상태인지 알려줄 수 있음
    * Retry-After는 날짜 형식으로 표기하거나 초 단위로 기입한다.

---

### 🌐 인증

* Authorization : 클라이언트에 인증 정보를 서버에 전달
    * `Authorization : Basic XXXXXXXXXXX`


* WWW-Authenticate : 리소스 접근시 필요한 인증 방법을 정의
    * `WWW-Authenticate: Newauth realm="apps", type=1, title="Login to" ...`
    * 401 Unauthorized 응답과 함께 사용

---

### 🍪 쿠키

#### HTTP의 Stateless의 해결책이 쿠키?

HTTP는 무상태성 프로토콜이다.   
때문에 하나의 요청과 요청에 대한 응답이 이뤄지면 연결이 끊어진다.  
**그렇기에 쿠키라는 해결책이 등장했다.**

#### 동작방식

1. 서버는 요청에 대한 응답 헤더에 Set-Cookie를 사용해서 유저에 대한 정보를 같이 보낸다.
2. 웹브라우저에는 쿠키 저장소가 존재하는데 Set-Cookie에 있는 값을 쿠키 저장소에 저장한다.
3. 웹브라우저는 이후에 같은 웹페이지에 접속할 때 반드시 Cookie 값을 자동으로 포함해서 보낸다.
4. 서버는 그 Cookie 값을 보고 사용자를 인식한다.

#### 쿠키 사용처와 특징

* 사용자 로그인 세션 관리
* 광고 정보 트래킹
* 쿠키 정보는 항상 서버에 전송되어야 한다.
* 최소한의 정보만 사용해야 한다. (세션 아이디, 인증토큰 등)
* 보안에 민감한 데이터는 저장하면 안된다. (주민등록번호, 신용카드 정보)
* 서버에 전송하지 않고 웹브라우저 내부에서 데이터를 저장하고 싶다면 웹 스토리지를 사용해야 한다.

#### 쿠키 헤더

* Set-Cookie : 서버에서 클라이언트로 쿠키를 전달 (응답)
    * `set-cookie: sessionId=abcd1234; expires=Sat, 26-Dec-2020 00:00:00 GMT; path=/; domain=google.com; Secure`
    * expires : 쿠키 만료일 (날짜 형식)
    * max-age : 쿠키 시간 (초 단위)
    * 세션 쿠키 : 만료날짜가 없을 때는 브라우저 종료시 쿠키 삭제
    * 영속 쿠키 : 만료날짜가 있을 때는 해당 날짜가 지날시 쿠키 삭제
    * domain : 명시한 문서 기준 도메인 + 서브 도메인 포함
    * path : 기입된 경로를 포함한 하위 경로 페이지만 쿠키로 접근 (일반적으로는 / 로 지정)


* Cookie : 클라이언트에서 서버에서 전달 받은 쿠키를 저장하고 HTTP 요청시 서버로 전달


#### 쿠키 보안  (Set-Cookie에서 사용)

* Secure : https 에서만 쿠키 전송
* HttpOnly : XSS 공격 방지, 자바스크립트에서 접근 불가능, HTTP 전송에만 사용
* SameSite : XSRF 공격 방지, 요청 도메인과 쿠키에 설정된 도메인지 같을 때만 전송



