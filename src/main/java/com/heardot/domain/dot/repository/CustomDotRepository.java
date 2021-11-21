package com.heardot.domain.dot.repository;

import com.heardot.domain.dot.Dot;
import com.heardot.domain.member.Member;

import java.util.List;

public interface CustomDotRepository {
    
    List<Dot> findDotsByMonth(Member member, int month);

    List<Dot> findDots(Member member);
}
