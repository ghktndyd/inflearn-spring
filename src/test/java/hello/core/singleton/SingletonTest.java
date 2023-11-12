package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @DisplayName("스프링 없는 순수한 Di 컨테이너")
    @Test
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 1. 조회 : 호출 할 때마다 객체를 생성한다.
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        // 콘솔 출력된 것을 보면 호출 할 때마다 당연하게도 다른 객체를 생성한다.
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    @Test
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        // same은 객체 참조를 비교하는 것이다.
        assertThat(singletonService1).isSameAs(singletonService2);

        // equal는 자바에서 equals랑 똑같다.
        assertThat(singletonService1).isEqualTo(singletonService2);
    }

}
