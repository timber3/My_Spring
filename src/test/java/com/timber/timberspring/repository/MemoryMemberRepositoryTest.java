package com.timber.timberspring.repository;

import com.timber.timberspring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // test는 서로 영향을 미치지 않게 작성이 되어야 한다.
    // 그러기 위해서는 각각의 테스트를 진행하면서 생성한 객체나 저장된 데이터들을 지워줄 필요가 있다.
    // 그러기 위한 코드 ( 각 테스트가 끝나면 실행 됨 )
    @AfterEach
    public void afterEach()
    {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("SH");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName()
    {
        Member member1 = new Member();
        member1.setName("SH1");
        repository.save(member1);

        Member member2 = new Member(); // shift + f6 으로 변수 이름 한번에 변경 가능
        member2.setName("SH2");
        repository.save(member2);

        Member result = repository.findByName("SH1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll()
    {
        Member member1 = new Member();
        member1.setName("SH1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("SH2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

    // TTD : 테스트 주도 개발 ( 테스트를 먼저 만들어놓고 제품을 개발한 후 테스트가 통과하는지 알아보는 개발 )
}
