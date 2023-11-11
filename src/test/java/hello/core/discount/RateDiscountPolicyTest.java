package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @DisplayName("VIP는 10% 할인 적용 O")
    @Test
    void vip_o() {
        // given
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        // when
        int discount = discountPolicy.discount(member, 10000);
        // then
        assertThat(discount).isEqualTo(1000);
    }

    @DisplayName("VIP가 아니면 10% 할인 X")
    @Test
    void vip_x() {
        // given
        Member memberBasic = new Member(2L, "memberBasic", Grade.BASIC);
        // when
        int discount = discountPolicy.discount(memberBasic, 10000);
        //then
        assertThat(discount).isEqualTo(0);
    }
}
