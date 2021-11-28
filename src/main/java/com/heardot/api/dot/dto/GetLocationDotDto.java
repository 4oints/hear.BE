package com.heardot.api.dot.dto;

import com.heardot.domain.dot.Dot;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

public class GetLocationDotDto {

    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    @Builder
    @ApiModel(value = "노래, 위치 정보 포함 닷 목록 반환 객체", description = "노래, 위치 정보 포함 닷 목록 반환 객체")
    public static class Response {

        @ApiModelProperty(value = "닷 ID")
        private Long dotId;

        @ApiModelProperty(value = "앨범아트")
        private String albumArt;

        @ApiModelProperty(value = "노래 제목")
        private String musicName;

        @ApiModelProperty(value = "스트리밍 사이트")
        private String siteType;

        @ApiModelProperty(value = "아티스트")
        private String artist;

        @ApiModelProperty(value = "위도")
        private String latitude;

        @ApiModelProperty(value = "경도")
        private String longitude;

        public static Response create(Dot dot) {
            return Response.builder()
                    .dotId(dot.getDotId())
                    .albumArt(dot.getMusic().getAlbumArt())
                    .musicName(dot.getMusic().getMusicName())
                    .artist(dot.getMusic().getArtist())
                    .siteType(dot.getMusic().getSiteType().name())
                    .latitude(dot.getLatitude())
                    .longitude(dot.getLongitude())
                    .build();
        }
    }
}
