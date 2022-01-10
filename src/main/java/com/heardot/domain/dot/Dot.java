package com.heardot.domain.dot;

import com.heardot.api.dot.dto.CreateDotDto;
import com.heardot.domain.BaseEntity;
import com.heardot.domain.member.Member;
import com.heardot.domain.music.Music;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "dot")
@Getter
@ToString(exclude = {"music","member"})
@AllArgsConstructor @NoArgsConstructor
public class Dot extends BaseEntity {

    @Id
    @Column(name = "dot_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dotId;

    @Column(nullable = false)
    private String regionNickname;

    private String comment;

    @Column(nullable = false)
    private String latitude; //위도

    @Column(nullable = false)
    private String longitude; //경도

    @Column(nullable = false)
    private String pictureId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "music_id")
    private Music music;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Dot(CreateDotDto.Request request, Member member) {
        this.latitude = request.getLatitude();
        this.longitude = request.getLongitude();
        this.regionNickname = request.getRegionNickname();
        this.comment = request.getComment();
        this.member = member;
        this.pictureId = request.getPictureId();
        this.music = Music.builder().musicName(request.getMusicName()).musicUrl(request.getMusicUrl()).artist(request.getArtist())
                .siteType(request.getSiteType()).build();
        connMemberDot(member);
    }

    private void connMemberDot(Member member) {
        this.member = member;
        member.getDots().add(this);
    }

    public Boolean isOwner(Long memberId) {
        return this.member.getMemberId().equals(memberId);
    }

    public void updateComment(String comment) {
        this.comment = comment;
    }

    public void updateRegionNickname(String regionNickname) {
        this.regionNickname = regionNickname;
    }

}
