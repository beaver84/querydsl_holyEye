package study.querydsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.querydsl.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}