package com.heardot.domain.music;

import com.heardot.domain.BaseEntity;
import com.heardot.domain.dot.Dot;
import com.heardot.domain.music.constant.SiteType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "music")
@Getter
@ToString(exclude = "dot")
@AllArgsConstructor @NoArgsConstructor
public class Music extends BaseEntity {

    @Id
    @Column(name = "music_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long musicId;

    @Column(nullable = false)
    private String musicName;

    @Column(nullable = false)
    private String musicUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SiteType siteType;

    @Lob
    @Column(nullable = false)
    private String albumArt;

    @OneToOne(fetch = FetchType.LAZY , mappedBy = "music")
    private Dot dot;

    @Builder
    public Music(String musicName, String musicUrl, String siteType, String albumArt) {
        this.musicName = musicName;
        this.musicUrl = musicUrl;
        this.siteType = SiteType.from(siteType);
        this.albumArt = albumArt;
    }


    public void update(String musicName, String musicUrl, String siteType, String albumArt) {
        this.musicUrl = musicUrl;
        this.musicName = musicName;
        this.siteType = SiteType.from(siteType);
        this.albumArt = albumArt;
    }
}
