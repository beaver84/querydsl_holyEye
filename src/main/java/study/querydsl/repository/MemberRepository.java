package study.querydsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.querydsl.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}