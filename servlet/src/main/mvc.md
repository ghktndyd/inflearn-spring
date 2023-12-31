## MVC 패턴 개요

### 너무 많은 역할

하나의 서블릿이나 Jsp만으로 비즈니스 로직과 뷰 랜더링을 모두 처리하게 되면 너무 많은 역할을 하게 된다.
결과적으로는 유지보수가 어려워진다. 비즈니스 로직을 호출하는 부분에 변경이 발생해도 손대야 하고, UI를 변경해도 손대야 한다.

### 변경의 라이프 사이클

UI를 일부 수정하는 일과 비즈니스 로직을 수정하는 일은 각각 다르게 발생할 가능성이 매우 높다. 대부분은 서로에게 영향이 없다.

### 기능 특화

Jsp같은 뷰 템플릿은 화면 랜더링을 하는 것에 최적화 되어 있기에 그 일을 하는 것에 집중하는 것이 좋다.

---

### 컨트롤러

HTTP 요청을 받아서 파라미터를 검증하고 비즈니스 로직을 실행한다.

#### 컨트롤러에 비즈니스 로직이 없는 이유

컨트롤러는 이미 많은 역할을 담당하고 있기 때문에 비즈니스 로직은 서비스라는 계층을 새로 생성해서 실행한다.

### 뷰

모델에 담겨있는 데이터를 이용해서 화면을 랜더링하는 것에 집중한다.

### 모델

뷰에 전달할 데이터를 담는 역할이다. 뷰가 랜더링 할 때 필요한 데이터가 담겨있다.

---

# MVC 패턴 - 한계

### 포워드의 중복

컨트롤러마다 dispatcher.forward(request, response); 코드가 중복이 된다.

### ViewPath의 중복

String viewPath가 중복된다. 

> 등등 여러 문제가 있다. 이 문제를 해결하려면 수문장 역할을 하는 기능이 필요하다.
> 그 기능을 해결하려면 **프론트 컨트롤러 패턴**을 도입해야 한다. 모든 요청들이 모두
> 프론트 컨트롤러를 거쳐서 들어오면 된다. (스프링 MVC의 핵심)

