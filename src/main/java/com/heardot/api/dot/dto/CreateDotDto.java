package com.heardot.api.dot.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDate;

public class CreateDotDto {

    @Getter
    @Setter
    @NoArgsConstructor @AllArgsConstructor
    @ApiModel(value = "Dot 생성 요청 객체", description = "Dot 생성 요청 객체")
    public static class Request {

        @ApiModelProperty(value = "음악 url")
        private String musicUrl;

        @ApiModelProperty(value = "음악 이름")
        private String musicName;

        @ApiModelProperty(value = "아티스트")
        private String artist;

        @ApiModelProperty(value = "음악 앨범 사진")
        private String albumArt;

        @ApiModelProperty(value = "음악 사이트 타입 (YOUTUBE)")
        private String siteType;

        @ApiModelProperty(value = "저장한 위도")
        private String latitude;

        @ApiModelProperty(value = "저장한 경도")
        private String longitude;

        @ApiModelProperty(value = "위치 별명")
        private String regionNickname;

        @ApiModelProperty(value = "한줄 코멘트")
        private String comment; // TODO 글자수 제한

    }

    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    @ApiModel(value = "Dot 생성 반환 객체", description = "Dot 생성 반환 객체")
    public static class Response {
        private Long dotId;

        public static Response create(Long dotId) {
            return new Response(dotId);
        }

    }

}
