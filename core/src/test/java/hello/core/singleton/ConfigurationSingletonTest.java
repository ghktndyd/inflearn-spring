package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();

        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig benn = ac.getBean(AppConfig.class);

        System.out.println("benn = " + benn.getClass());
        // benn = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$8ff06bed

        // 스프링이 CGLIB이라는 바이트코드 조작 라이브러리를 사용해서 AppConfig 클래스를 상속받은 임의 클래스를 만들고
        // 그 다른 클래스를 스프링 빈으로 등록한 것이다.
    }
}

// @Bean만 사용해도 스프링 빈으로 등록되지만 싱글톤을 보장하지 않는다.
// 고민할 것 없이 스프링 설정 정보에서는 반드시 @Configuration을 붙여주자