## 🌏 HTTP 설계 예시

### 🌐 HTTP API - 컬렉션

- POST 기반 등록 (예 : 회원관리 API 제공)
- URI 리소스를 식별하는 것은 리소스만 식별해야 한다. 즉 동사는 HTTP 메서드로 확인해야 한다.
    * 회원 목록 : /members (GET)
    * 회원 등록 : /members (POST)
    * 회원 조회 : /members/{id} (GET)
    * 회원 수정 : /members/{id} (PATCH, PUT, POST) / 수정을 할 때는 일반적으로는 PATCH를 쓴다.
    * 회원 삭제 : /members/{id} (DELETE)
- POST - 신규 자원 등록 특징
    * 클라이언트는 등록될 리소스의 URI를 모른다. (PUT으로 등록하면 리소스를 알고 있다.)
    * 서버가 새로 등록된 리소스의 URI를 생성해준다.
    * 위와 같은 형식은 **"Collection 컬렉션**" 이라고 한다.
        * 서버가 관리하는 리소스 디렉토리

---

### 🌐 HTTP API - 스코어

- PUT 기반 등록 (예 : 파일 관리 시스템)
- 파일 목록 : /files (GET)
- 파일 조회 : /files/{id} (GET)
- 파일 등록 : /files/{filename} (PUT)
- 파일 삭제 : /files/{id} (DELETE)
- 파일 대량 등록 : /files (POST)


- PUT : 있으면 덮어버리고, 없으면 새로 생성한다. (파일시스템에서 쓰는 이유는 있는 건 삭제해야 하기 때문에)
- URI를 보면 알겠지만 PUT은 클라이언트가 리소스의 URI를 알고 있어야 한다. `/files/{filename}`처럼
- 왜냐하면 클라이언트가 생성될 리소스의 URI를 관리하기 때문이다.
    - 위와 같은 형식은 **"Store 스토어"** 라고 한다.
        * 클라이언트가 관리하는 리소스 저장소
        * 클라이언트가 리소스 URI를 알아야 하며 직접 관리한다.

#### 🍃 대부분 POST 기반으로 동작하는 컬렉션을 사용한다.

---

### 🌐 HTML FORM 사용 (GET, POST만 지원)

- Ajax 같은 기술을 사용해서 API 통신으로 해결할 수 있다.
- 여기서의 조건은 순수 HTML의 FORM만 이야기 한다.
- 회원 관리 시스템
    * 회원 목록 : /members (GET)
    * 회원 등록 폼 : /members/new (GET)
    * 회원 등록 : /members/new 또는 /members (POST)
    * 회원 조회 : /members/{id} (GET)
    * 회원 수정 폼 : /members/{id}/edit (GET)
    * 회원 수정 : /members/{id}/edit 또는 /members/{id} (POST)
    * 회원 삭제 : /members/{id}/delete (POST)
- HTML FORM은 GET과 POST만 있기 때문에 제약이 있다.
    * 이런 제약을 해결하기 위해서 컨트롤 URI를 써야한다. 위의 예시 중에는
    * `회원 삭제 : /members/{id}/delete (POST)` 같은 것이다. (동사를 사용하는 것이 특징)
    * 실제로 많이 쓰이지만 최대한 HTTP 메서드로 해결하려고 노력한 다음에 쓰는 게 좋다.송

---

#### 🌐 참고하면 좋은 URI 설계 개념

* 문서 (document)
    * 단일 개념 (파일 하나, 객체 인스턴스, 데이터베이스 row 1개)


* 컬렉션 (collection)
    * 서버가 관리하는 리소스 디렉토리
    * 서버가 리소스의 URI를 직접 생성 및 관리


* 스토어 (store)
    * 클라이언트 관리하는 리소스 저장소
    * 클라이언트가 리소스의 URI를 알고 있으며 관리


* 컨트롤러 (controller), 컨트롤 URI
    * 문서, 컬렉션, 스토어로 해결하기 어려운 추가 프로세스 실행
    * 동사를 사용


