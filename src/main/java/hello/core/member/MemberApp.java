package hello.core.member;

import hello.core.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
        // With only Java DI
        // AppConfig appConfig = new AppConfig();
        // MemberService memberService = appConfig.memberService();

        // Without DI
        // MemberService memberService = new MemberServiceImpl();

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        Member memberA = new Member(1L, "Lee", Grade.VIP);

        memberService.join(memberA);
        Member foundMember = memberService.findMember(1L);
        System.out.println("Found member = " + foundMember.getName());
        System.out.println("Original member = " + memberA.getName());
    }
}
