package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
public class RequestParamController {

	@RequestMapping("/request-param-v1")
	public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		int age = Integer.parseInt(request.getParameter("age"));

		log.info("username = {}, age = {}", username, age);

		response.getWriter().write("ok");
	}

	@ResponseBody // RestController와 같은 효과, view를 리턴 하는 게 아닌 값 자체를 리턴한다.
	@RequestMapping("/request-param-v2")
	public String requestParamV2(@RequestParam("username") String memberName,
		@RequestParam("age") int memberAge) {
		log.info("memberName = {}, memberAge = {}", memberName, memberAge);
		return "ok";
	}

	@ResponseBody // RestController와 같은 효과, view를 리턴 하는 게 아닌 값 자체를 리턴한다.
	@RequestMapping("/request-param-v3")
	public String requestParamV3(@RequestParam String username,  // 원래 가능했으나 스프링부트3으로 오면서 안되는 것.
		@RequestParam int age) {
		log.info("memberName = {}, memberAge = {}", username, age);
		return "ok";
	}

	@ResponseBody
	@RequestMapping("/request-param-required")
	public String requestParamRequired(@RequestParam(value = "username", required = false) String memberName,
		@RequestParam("age") int memberAge) {
		log.info("memberName = {}, memberAge = {}", memberName, memberAge);
		return "ok";
	}

	@ResponseBody
	@RequestMapping("/request-param-default")
	public String requestParamDefault(@RequestParam(value = "username", defaultValue = "guest") String memberName,
		@RequestParam(value = "age", defaultValue = "-1") int memberAge) {
		log.info("memberName = {}, memberAge = {}", memberName, memberAge);
		return "ok";

		// defaultValue를 설정하면 빈 문자인 경우에도 defaultValue가 할당된다.
	}

	@ResponseBody
	@RequestMapping("/request-param-map")
	public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
		log.info("username = {}, age = {}", paramMap.get("username"), paramMap.get("age"));
		return "ok";

		// 파라미터값이 확실하다면 Map으로 해도 되지만 그렇지 않다면
		/**
		 * MultiValueMap을 사용하자 ! 권장되는 방법이 MultiValueMap이다.
		 */
	}

	/**
	 // 	 * 이것이 원래 코드라면 아래 코드는 요즘 사용 가능
	 // 	 */
	// @ResponseBody
	// @RequestMapping("/model-attribute-v1")
	// public String modelAttributeV1(@RequestParam String username, @RequestParam int age) {
	//
	//
	//
	// 	HelloData helloData = new HelloData();
	// 	helloData.setUsername(username);
	// 	helloData.setAge(age);
	//
	// 	log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
	//
	// 	return "ok";
	//
	// }

	/**
	 *
	 * 위 메서드와 동일
	 *
	 */
	@ResponseBody
	@RequestMapping("/model-attribute-v1")
	public String modelAttributeV1(@ModelAttribute HelloData helloData) {
		log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
		return "ok";
		/**
		 * @ModelAttribute 실행 순서
		 * 1. HelloData 객체를 생성한다.
		 * 2. 요청 파라미터의 이름으로 HelloData 객체의 프로퍼티를 찾는다.
		 * 3. 해당 프로퍼티의 setter를 호출해서 파라미터에 바인딩한다.
		 * 3. 예시) HelloData에 만약 username이 있다면 setUsername()을 호출해서 값을 바인딩 한다는 뜻
		 */

		/**
		 * 프로퍼티 ? HelloData로 따지면 username과 age처럼 그 객체가 갖고 있는 속성.
		 */

		/**
		 * 바인딩 오류 ? int 프로퍼티에 만약 문자열 값이 들어간다면 에러가 발생한다.
		 */
	}

	@ResponseBody
	@RequestMapping("/model-attribute-v2")
	public String modelAttributeV2(HelloData helloData) {
		log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
		return "ok";
		// @ModelAttribute도 생략 가능하다.
		// 하지만 @RequestParam도 생략이 가능하다 그렇다면 스프링이 나누는 기준이 무엇일까?
		// String, int, Integer 처럼 단순 타입이라면 @RequestParam을 사용한다.
		// 그 외의 타입들은 @ModelAttribue를 사용한다. (argument resolver로 지정한 타입 외의 타입들)

	}
}
