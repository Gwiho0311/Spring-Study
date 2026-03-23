
package hello.hello_spring.repository;
import hello.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
/** * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
// 아직 데이터 저장소가 선정되지 않았다는 가상의 시나리오 속에서 개발을 먼저 진행하기 위해 메모리 기반 저장소를 직접 구현
    @Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); // DB대신 메모리에 저장하기 위해 Map 사용
    private static long sequence = 0L; // long 변수에 맞는 타입

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }
    public void clearStore() {
        store.clear();
    }
}