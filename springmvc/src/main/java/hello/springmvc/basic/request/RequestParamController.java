package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

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

}
