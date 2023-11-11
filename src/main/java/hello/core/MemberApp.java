package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();
//
//        MemberService memberService = appConfig.memberService();

        // 스프링 컨테이너에서 @Configuration이 붙은 클래스를 기반으로 찾아온다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        // Bean을 꺼내겠다. Bean의 이름은 memberService고, 타입은 MemberService 탕비이다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);

        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        System.out.println("new Member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());

    }
}
