package com.timber.timberspring.repository;

import com.timber.timberspring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();   // 동시성 문제를 고려 해야함. ( ConcurrentHashMap )
    private static long sequence = 0L;  // 동시성 문제를 고려해야 함 ( AtomicLong ) 0, 1, 2, ... key 값을 생성하는 변수

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  // get(id)의 결과가 Null 일 경우 에러가 나므로 Optional 으로 감싸서 ofNullable으로 처리한다.
        // 이렇게 되면 client에서 뭘 할수 있다고 함.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore()
    {
        store.clear();
    }
}
