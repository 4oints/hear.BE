package com.heardot.domain.posting;

import com.heardot.domain.Region.Region;
import com.heardot.domain.member.Member;
import com.heardot.domain.music.Music;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "posting")
@Getter
@ToString(exclude = {"music","member"})
@AllArgsConstructor @NoArgsConstructor
public class Posting {

    @Id
    @Column(name = "posting_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    private String regionNickname;

    private String comment;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "posting", cascade = CascadeType.ALL, orphanRemoval = true)
    private Music music;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
