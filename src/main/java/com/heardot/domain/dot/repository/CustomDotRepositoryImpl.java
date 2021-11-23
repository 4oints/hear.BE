package com.heardot.domain.dot.repository;

import com.heardot.domain.dot.Dot;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.heardot.domain.dot.QDot.dot;
import static com.heardot.domain.music.QMusic.music;

public class CustomDotRepositoryImpl implements CustomDotRepository{

    @Autowired JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Dot> findDotsByMonth(Long memberId, int month) {
        return jpaQueryFactory
                .selectFrom(dot)
                .innerJoin(dot.music, music).fetchJoin()
                .where(dot.member.memberId.eq(memberId)
                        .and(dot.regTime.month().eq(month)))
                .fetch();
    }

    @Override
    public List<Dot> findDots(Long memberId) {
        return jpaQueryFactory
                .selectFrom(dot)
                .innerJoin(dot.music, music).fetchJoin()
                .where(dot.member.memberId.eq(memberId))
                .fetch();
    }

    public Long countByLocation(Long memberId, String latitude, String longitude) {
        return jpaQueryFactory.selectFrom(dot)
                .where(dot.member.memberId.eq(memberId)
                        .and(dot.latitude.eq(latitude))
                        .and(dot.longitude.eq(longitude)))
                .fetchCount();
    }

    @Override
    public List<Dot> findWithMusicByLocation(Long memberId, String latitude, String longitude) {
        return jpaQueryFactory.selectFrom(dot)
                .innerJoin(dot.music, music).fetchJoin()
                .where(dot.member.memberId.eq(memberId)
                        .and(dot.latitude.eq(latitude))
                        .and(dot.longitude.eq(longitude)))
                .fetch();
    }

}
