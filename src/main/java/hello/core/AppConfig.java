package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 설정 정보를 담당하는 클래스에 붙이는 어노테이션
public class AppConfig {

    // @Bean memberService()이 한 번 호출이 되면
    // new MemoryMemberRepository()는 같이 호출이 된다. = getMemberRepository()의 구현부

    // @Bean orderService()도 한 번 호출이 되면
    // new OrderServiceImpl()이 호출이 되는데 이때 new MemoryMemberRepository()는 같이 호출이 된다.

    // new 키워드로 계속 객체를 생성이 되는데 싱글톤이 깨지는 게 아닌가?


    @Bean // 스프링 컨테이너가 관리하는 객체로 설정하는 어노테이션
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

}
