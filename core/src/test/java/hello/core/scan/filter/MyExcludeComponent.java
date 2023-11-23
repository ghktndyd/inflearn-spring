package hello.core.scan.filter;

import java.lang.annotation.*;

/**
 * 어노테이션을 만드는 방법
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {
}
