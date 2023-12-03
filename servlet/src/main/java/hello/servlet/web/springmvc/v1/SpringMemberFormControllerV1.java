package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// @Component
// @RequestMapping 두 개를 붙여도 인식한다. 다만 메서드에 붙어있으면 안된다.
@Controller // 스프링이 자동으로 스프링 빈으로 등록한다. -> @Component 어노테이션을 갖고 있음, 또 스프링이 어노테이션 기반 컨트롤러로 인식
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form") // 요청 정보를 매핑한다. 해당 url이 호출되면 이 메서드를 호출한다.
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}
