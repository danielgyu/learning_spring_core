package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.inject.Provider;

public class JavaProviderTest {

    @Test
    void objectFactoryFind() {
        AnnotationConfigApplicationContext ac2 = new AnnotationConfigApplicationContext(JavaFactorySingletonBean.class, JavaFactoryMyBean.class);
        JavaFactorySingletonBean b1 = ac2.getBean(JavaFactorySingletonBean.class);
        JavaFactorySingletonBean b2 = ac2.getBean(JavaFactorySingletonBean.class);
        Assertions.assertThat(b1.logic()).isEqualTo(1);
        Assertions.assertThat(b2.logic()).isEqualTo(1);
    }

    @Scope("singleton")
    static class JavaFactorySingletonBean {
        private final Provider<JavaFactoryMyBean> beanProvider;

        @Autowired
        public JavaFactorySingletonBean(Provider<JavaFactoryMyBean> beanProvider) {
            this.beanProvider = beanProvider;
        }

        public int logic() {
            JavaFactoryMyBean myBean = this.beanProvider.get();
            myBean.addCount();
            return myBean.getCount();
        }
    }

    @Scope("prototype")
    static class JavaFactoryMyBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }
}
