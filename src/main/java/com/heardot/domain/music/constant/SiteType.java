package com.heardot.domain.music.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.heardot.domain.member.constant.SocialType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum SiteType {
    YOUTUBE;

    @JsonCreator
    public static SiteType from(String type) {
        return SiteType.valueOf(type.toUpperCase());
    }

    public static boolean isSiteType(String type) {
        List<SiteType> collect = Arrays.stream(SiteType.values())
                .filter(SiteType -> SiteType.name().equals(type))
                .collect(Collectors.toList());

        if(collect == null || collect.size() == 0) {
            return false;
        }

        return true;
    }

}
