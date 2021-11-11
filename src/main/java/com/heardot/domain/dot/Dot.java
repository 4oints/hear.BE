package com.heardot.domain.dot;

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

    private String regionNickname;

    private String comment;

    private String latitude; //위도

    private String longitude; //경도

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Music music;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Dot(String latitude, String longitude, String regionNickname, String comment,
               String musicUrl, String musicName, String siteType, String albumArt, Member member) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.regionNickname = regionNickname;
        this.comment = comment;
        this.member = member;
        this.music = Music.builder().musicName(musicName).musicUrl(musicUrl).siteType(siteType).albumArt(albumArt).build();
        connMemberDot(member);
    }

    private void connMemberDot(Member member) {
        this.member = member;
        member.getDots().add(this);
    }

    public boolean isOwner(Member member) {
        return this.member.equals(member);
    }

    public void updateComment(String comment) {
        this.comment = comment;
    }

    public void updateRegionNickname(String regionNickname) {
        this.regionNickname = regionNickname;
    }

}
