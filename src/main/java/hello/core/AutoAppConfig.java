package hello.core;

import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core.member", // <- 이 패키지의 하위 패키지부터 컴포넌트 스캔을 시작한다.
        basePackageClasses = AutoAppConfig.class,
        excludeFilters= @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
) // 컴포넌트 스캔을 하는데 @Configuration이 붙은 클래스는 컴포넌트 스캔 대상에서 제외한다.
// @ComponentScan 은 이름 그대로 @Component가 붙은 클래스를 스캔해서 스프링 빈으로 등록한다.
public class AutoAppConfig {

    @Bean(name = "memoryMemberRepository")
    MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}

// 지정하지 않으면 default packages는 @ComponentScan이 붙은 클래스가 스캔의 시작 위치가 된다.
// 그렇기에 Config 파일들은 설정 정보를 패키지에 두지 않고 최상위에 위치 시키는 것이 최근의 트렌드이다.
// 쉽게 말하면 @SpringBootApplication 클래스랑 같은 위치에 두는 것이 관례라는 것이다.
// @SpringBootApplication도 @ComponentScan이 붙어있따.
// @ComponentScan의 대상
/**
 * 1. @Component = 컴포넌트 스캔에서 사용
 * 2. @Controller = 스프링 mvc 컨트롤러에서 사용 -> 컨트롤러로 인식한다.
 * 3. @Service = 스프링 비즈니스 로직에서 사용 -> 특별한 기능은 없지만 비즈니스 로직이 이곳에 있겠다라고 인식하는데 도움을 준다.
 * 4. @Repository = 스프링 DB 접근 계층에서 사용 -> 스프링의 데이터 접근계층으로 인식한다. 데이터 계층의 예외를 스프링 예외로 변환해준다.
 * 5. @Configuration = 스프링 설정 정보에서 사용 -> 스프링 설정 정보로 인식하고 스프링 빈이 싱글톤을 유지하도록 해준다.
 **/