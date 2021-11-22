package com.heardot.domain.dot.repository;

import com.heardot.domain.dot.Dot;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DotRepository extends JpaRepository<Dot, Long>, CustomDotRepository {

    @EntityGraph(attributePaths = {"music"})
    Optional<Dot> findWithMemberMusicByDotId(Long dotId);

}
