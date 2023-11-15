package hello.core.scan;

import hello.core.member.MemberService;
import hello.core.AutoAppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);

        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}

/**
 * @ComponentScan은 @Component가 붙은 모든 클래스를 스프링 빈으로 등록한다.
 * 이때 스프링 빈의 기본이름은 클래스명의 맨 앞글자를 소문자로 변환하여 사용된다.
 * ex) MemberServiceImpl -> memberServiceImpl 로 사용된다.
 * 스프링 빈의 이름을 직접 지정하고 싶으면 @Component("빈이름")
 */

/**
 * 생성자에 @Autowired가 붙어있으면 스프링 컨테이너가 자동으로 해당 스프링 빈을 찾아서 주입한다.
 * 첫 번째로 타입이 같은 빈을 찾아서 주입한다.
 */

/**
 * ConflictingBeanDefinitionException -> 빈 중에 이름이 같은 게 있다.
 * 최근 스프링부트는 수동 빈 등록과 자동 빈 등록이 겹친다면 에러를 발생시킨다.
 */