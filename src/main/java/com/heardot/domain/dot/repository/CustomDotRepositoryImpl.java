package com.heardot.domain.dot.repository;

import com.heardot.domain.dot.Dot;
import com.heardot.domain.dot.QDot;
import com.heardot.domain.member.Member;
import com.heardot.domain.member.QMember;
import com.heardot.domain.music.QMusic;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.heardot.domain.dot.QDot.dot;
import static com.heardot.domain.member.QMember.member;
import static com.heardot.domain.music.QMusic.music;

public class CustomDotRepositoryImpl implements CustomDotRepository{

    @Autowired JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Dot> findDotsByMonth(Member member, int month) {
        return jpaQueryFactory
                .selectFrom(dot)
                .leftJoin(dot.music, music).fetchJoin()
                .where(dot.member.eq(member)
                        .and(dot.regTime.month().eq(month)))
                .fetch();
    }

    @Override
    public List<Dot> findDots(Member member) {
        return jpaQueryFactory
                .selectFrom(dot)
                .leftJoin(dot.music, music).fetchJoin()
                .where(dot.member.eq(member))
                .fetch();
    }

}
