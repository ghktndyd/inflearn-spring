package jpabook.jpashop.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;

@RunWith(SpringRunner.class) // Spring과 같이 사용한다.
@Transactional // 테스트에서 된 결과가 롤백된다.
@SpringBootTest // 스프링부트 컨테이너 안에서 테스트를 돌린다.
public class MemberServiceTest {

	@Autowired
	MemberService memberService;

	@Autowired
	MemberRepository memberRepository;

	@Test
	public void 회원가입() throws Exception {
		//Given
		Member member = new Member();
		member.setName("kim");

		//When
		Long savedId = memberService.join(member);

		//Then
		assertEquals(member, memberRepository.findOne(savedId));
	}

	@Test(expected = IllegalStateException.class) // 발생한 예외가 IllegalStateException.class이라면 코드가 의도한대로 동작한 것이다.
	public void 중복_회원_예외() throws Exception {
		//Given
		Member member1 = new Member();
		member1.setName("kim");
		Member member2 = new Member();
		member2.setName("kim");

		//When
		memberService.join(member1);
		memberService.join(member2); //예외가 발생해야 한다.

		//Then
		fail("예외가 발생해야 한다.");
	}

}