package hello.core.discount;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10%할인 적용")
    void vipDiscount() {
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        int discountAmount = discountPolicy.discount(member, 1000);

        assertThat(discountAmount).isEqualTo(100);
    }

    @Test
    @DisplayName("VIP가 아니면 할인 적용X")
    void vipDiscountNone() {
        Member member = new Member(1L, "memberVIP", Grade.BASIC);
        int discountAmount = discountPolicy.discount(member, 1000);

        assertThat(discountAmount).isEqualTo(0);
    }
}