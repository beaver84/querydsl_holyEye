package study.querydsl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.querydsl.entity.Member;
import study.querydsl.entity.Team;
import study.querydsl.repository.MemberJPARepository;
import study.querydsl.repository.MemberRepository;
import study.querydsl.repository.TeamRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
public class N1Test {

    @Autowired
    private EntityManager em;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;

    @Test
    @DisplayName("N + 1 실패 test")
    void N1_Test_Fail() throws Exception {
        //member1 -> teamA
        //member2 -> teamB
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        teamRepository.save(teamA);
        teamRepository.save(teamB);
        memberRepository.save(new Member("member1", 10, teamA));
        memberRepository.save(new Member("member2", 20, teamB));

        em.flush();
        em.clear();

        //when
        List<Member> members = memberRepository.findAll();

        //then
        for (Member member : members) {
            member.getTeam().getName();
        }
    }
}
