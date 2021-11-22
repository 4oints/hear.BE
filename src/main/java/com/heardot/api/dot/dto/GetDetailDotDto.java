package com.heardot.api.dot.dto;

import com.heardot.domain.dot.Dot;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GetDetailDotDto {

    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    @Builder
    @ApiModel(value = "Dot 상세 정보 조회 반환 객체", description = "Dot 상세 정보 조회 반환 객체")
    public static class Response {

        @ApiModelProperty(value = "앨범아트")
        private String albumArt;

        @ApiModelProperty(value = "노래 제목")
        private String musicName;

        @ApiModelProperty(value = "노래 url")
        private String musicUrl;

        @ApiModelProperty(value = "아티스트")
        private String artist;

        @ApiModelProperty(value = "위도")
        private String latitude;

        @ApiModelProperty(value = "경도")
        private String longitude;

        @ApiModelProperty(value = "위치 별명")
        private String regionNickname;

        @ApiModelProperty(value = "한 줄 코멘트")
        private String comment;

        @ApiModelProperty(value = "저장한 날짜")
        private String regTime;

        public static Response create(Dot dot) {
            return Response.builder()
                    .albumArt(dot.getMusic().getAlbumArt())
                    .musicName(dot.getMusic().getMusicName())
                    .musicUrl(dot.getMusic().getMusicUrl())
                    .artist(dot.getMusic().getArtist())
                    .latitude(dot.getLatitude())
                    .longitude(dot.getLongitude())
                    .regionNickname(dot.getRegionNickname())
                    .comment(dot.getComment())
                    .regTime(dot.getRegTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .build();
        }
    }
}