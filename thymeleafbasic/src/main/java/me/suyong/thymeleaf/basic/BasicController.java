package me.suyong.thymeleaf.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Data;

@RequestMapping("/basic")
@Controller
public class BasicController {

	@GetMapping("/text-basic")
	public String textBasic(Model model) {
		model.addAttribute("data", "Hello Spring");
		return "basic/text-basic";
	}

	@GetMapping("/text-unescaped")
	public String textUnescaped(Model model) {
		model.addAttribute("data", "Hello, <b>Spring</b>");
		return "basic/text-unescaped";
	}

	@GetMapping("/variable")
	public String variable(Model model) {
		User userA = new User("userA", 10);
		User userB = new User("userB", 15);

		List<User> users = new ArrayList<>();
		users.add(userA);
		users.add(userB);

		Map<String, User> userMap = new HashMap<>();
		userMap.put("userA", userA);
		userMap.put("userB", userB);

		model.addAttribute("user", userA);
		model.addAttribute("users", users);
		model.addAttribute("userMap", userMap);

		return "basic/variable";
	}

	@Data
	public class User {
		private String username;
		private int age;

		public User(String username, int age) {
			this.username = username;
			this.age = age;
		}
	}
}
