요청 파라미터를 조회할 때는

* @RequestParam
* @ModelAttribute

Http 메세지 바디를 조회할 때는

* @RequestBody

---

#### 스프링 MVC는 다음 경우에 HTTP 메세지 컨버터를 적용한다.

HTTP 요청

- `@RequestBody`
- `HttpEntity(RequestEntity)`

HTTP 응답

- `@ResponseBody`
- `HttpEntity(ResponseEntity)`


