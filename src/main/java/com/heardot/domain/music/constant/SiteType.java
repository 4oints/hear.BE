package com.heardot.domain.music.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SiteType {
    YOUTUBE("YOUTUBE", "유튜브");

    private final String key;
    private final String title;

}
