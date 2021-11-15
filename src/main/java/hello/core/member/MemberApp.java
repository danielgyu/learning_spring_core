package hello.core.member;

public class MemberApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member memberA = new Member(1L, "Lee", Grade.VIP);

        memberService.join(memberA);
        Member foundMember = memberService.findMember(1L);
        System.out.println("Found member = " + foundMember.getName());
        System.out.println("Original member = " + memberA.getName());
    }
}
