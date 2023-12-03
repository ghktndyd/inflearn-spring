package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("/springmvc/old-controller") // 스프링 빈의 이름을 url 패턴으로 맞춘 것
public class OldController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return new ModelAndView("new-form");

        // InternalResourceViewResolver를 통해서 View를 만들어준다. (application.properties에서 설정하면 스프링부트가 자동으로 해줌)
    }

    /**
     * 컨트롤러 매핑 순서
     *
     * 1. 핸들러 매핑에서 OldController를 찾는다.
     * 2. 핸들러를 찾았다면 그 핸들러를 실행할 수 있는 어댑터를 찾아본다. -> 핸들러 어댑터 목록에서
     * 3. 찾은 핸들러를 핸들러 어댑터로 넘긴다.
     * 4. 핸들러 어댑터는 컨트롤러를 호출한다.
     */

    /**
     * 핸들러 매핑 순위
     * 0위 : RequestMappingHandlerMapping : 애너테이션 기반의 컨트롤러 매핑
     * 1위 : BeanNameUrlHandlerMapping : Url의 이름과 똑같은 스프링 빈의 이름을 가진 핸들러를 찾는다.
     */

    /**
     * 핸들러 어댑터 순위
     * 0위 : RequestMappingHandlerAdapter : 애너테이션 기반의 컨트롤러 매핑
     * 1위 : HttpRequestHandlerAdapter : HttpRequestHandler 기반
     * 2위 : SimpleControllerHandlerAdapter : 인터페이스 Controller 기반으로 처리
     */
}
