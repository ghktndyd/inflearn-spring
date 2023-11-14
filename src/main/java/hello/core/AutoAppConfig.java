package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
) // 컴포넌트 스캔을 하는데 @Configuration이 붙은 클래스는 컴포넌트 스캔 대상에서 제외한다.
// @ComponentScan 은 이름 그대로 @Component가 붙은 클래스를 스캔해서 스프링 빈으로 등록한다.
public class AutoAppConfig {

}
