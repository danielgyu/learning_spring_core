package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class ObjectFactoryTest {

    @Test
    void singletonFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ObjectFactorySingletonBean.class, ObjectFactoryMyBean.class);
        ObjectFactorySingletonBean bean1 = ac.getBean(ObjectFactorySingletonBean.class);
        ObjectFactorySingletonBean bean2 = ac.getBean(ObjectFactorySingletonBean.class);
        Assertions.assertThat(bean1.logic()).isEqualTo(1);
        Assertions.assertThat(bean2.logic()).isEqualTo(1);
    }

    @Test
    void objectFactoryFind() {
        AnnotationConfigApplicationContext ac2 = new AnnotationConfigApplicationContext(ObjectFactorySingletonBean2.class, ObjectFactoryMyBean.class);
        ObjectFactorySingletonBean2 b1 = ac2.getBean(ObjectFactorySingletonBean2.class);
        ObjectFactorySingletonBean2 b2 = ac2.getBean(ObjectFactorySingletonBean2.class);
        Assertions.assertThat(b1.logic()).isEqualTo(1);
        Assertions.assertThat(b2.logic()).isEqualTo(1);
    }

    @Scope("singleton")
    static class ObjectFactorySingletonBean2 {
        private final ObjectProvider<ObjectFactoryMyBean> beanProvider;

        @Autowired
        public ObjectFactorySingletonBean2(ObjectProvider<ObjectFactoryMyBean> beanProvider) {
            this.beanProvider = beanProvider;
        }

        public int logic() {
            ObjectFactoryMyBean myBean = this.beanProvider.getObject();
            myBean.addCount();
            return myBean.getCount();
        }
    }

    @Scope("singleton")
    static class ObjectFactorySingletonBean {
        private final ApplicationContext ac;

        @Autowired
        public ObjectFactorySingletonBean(ApplicationContext ac) {
            this.ac = ac;
        }

        public int logic() {
            ObjectFactoryMyBean myBean = this.ac.getBean(ObjectFactoryMyBean.class);
            myBean.addCount();
            return myBean.getCount();
        }
    }

    @Scope("prototype")
    static class ObjectFactoryMyBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }
    }
}
