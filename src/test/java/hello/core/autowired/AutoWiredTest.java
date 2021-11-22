package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutoWiredTest {

    @Test
    void autowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {
        @Autowired(required = false)
        public void setBean1(Member noBean1) {
            System.out.println("no Bean1:" + noBean1);
        }

        @Autowired
        public void setBean2(@Nullable Member noBean2) {
            System.out.println("no Bean2:" + noBean2);
        }

        @Autowired
        public void setBean3(Optional<Member> noBean3) {
            System.out.println("no Bean3:" + noBean3);
        }
    }
}
