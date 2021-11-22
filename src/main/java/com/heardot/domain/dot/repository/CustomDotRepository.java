package com.heardot.domain.dot.repository;

import com.heardot.domain.dot.Dot;
import com.heardot.domain.member.Member;

import java.util.List;

public interface CustomDotRepository {
    
    List<Dot> findDotsByMonth(Long memberId, int month);

    List<Dot> findDots(Long memberId);
}
