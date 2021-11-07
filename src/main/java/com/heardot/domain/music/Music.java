package com.heardot.domain.music;

import com.heardot.domain.music.constant.SiteType;
import com.heardot.domain.posting.Posting;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "music")
@Getter
@ToString(exclude = "posting")
@AllArgsConstructor @NoArgsConstructor
public class Music {

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

    @OneToOne(fetch = FetchType.LAZY)
    private Posting posting;
}
