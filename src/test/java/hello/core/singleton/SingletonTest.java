package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("Pure DI continaer without Spring")
    void pureContainer() {
        AppConfig ac = new AppConfig();
        MemberService memberService1 = ac.memberService();
        MemberService memberService2 = ac.memberService();

        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("Use singleton patter")
    void singletonContainer() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();
        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("Singleton Spring container")
    void springContainer() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService ms1 = ac.getBean("memberService", MemberService.class);
        MemberService ms2 = ac.getBean("memberService", MemberService.class);

        Assertions.assertThat(ms1).isSameAs(ms2);
    }
}
