package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA : 유저 A가 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);

        // ThreadB : 유저 B가 20000원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        // ThreadA : 사용자 A가 주문 금액 조회
        // int price = statefulService1.getPrice();
        System.out.println("userAPrice = " + userAPrice);

        // 기대했던 건 10,000원이지만 실제 값은 20,000원이 나왔다.
        // userA가 가격을 조회하기 전 찰나의 순간에 userB가 20,000원의 주문을 해버렸기 때문이다.

//        Assertions.assertThat(statefulService1.getPrice())
//                .isEqualTo(20000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
