package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @DisplayName("빈 이름으로 조회")
    @Test
    void findBeanByName() {

        MemberService memberService = ac.getBean("memberService", MemberService.class);

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    }

    @DisplayName("타입으로 조회")
    @Test
    void findBeanByType() {

        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    }

    @DisplayName("빈 이름과 구현체 타입으로 조회 / 좋은 방법은 아님")
    @Test
    void findBeanByName2() {

        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    }

    @DisplayName("빈 이름으로 조회 X")
    @Test
    void findByNameX() {

        assertThrows(NoSuchBeanDefinitionException.class,
                 () ->  ac.getBean("Xxxx", MemberService.class));

        /**
         *  ac.getBean("Xxxx", MemberService.class) 메서드를 실행하면
         *  NoSuchBeanDefinitionException이 반드시 터져야 한다. (안
         */
    }
}
