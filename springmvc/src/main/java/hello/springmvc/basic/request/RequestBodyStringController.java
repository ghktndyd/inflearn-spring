package hello.springmvc.basic.request;

import static org.springframework.util.StreamUtils.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RequestBodyStringController {

	@PostMapping("/request-body-string-v1")
	public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {

		ServletInputStream inputStream = request.getInputStream();

		String messageBody = copyToString(inputStream, StandardCharsets.UTF_8);

		log.info("messageBody = {}", messageBody);

		response.getWriter().write("ok");
	}

	@PostMapping("/request-body-string-v2")
	public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {

		String messageBody = copyToString(inputStream, StandardCharsets.UTF_8);

		log.info("messageBody = {}", messageBody);

		responseWriter.write("ok");
	}

	@PostMapping("/request-body-string-v3")
	public HttpEntity requestBodyStringV3(HttpEntity<String> httpEntity) {

		log.info("messageBody = {}", httpEntity.getBody());

		return new HttpEntity<>("ok");
	}

	@ResponseBody
	@PostMapping("/request-body-string-v4")
	public String requestBodyStringV4(@RequestBody String messageBody) {

		log.info("messageBody = {}", messageBody);

		return "ok";
	}

}
