package com.heardot.api.dot.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

public class UpdateDotDto {

    @Getter @Setter
    @ApiModel(value = "Dot 업데이트 요청 객체", description = "Dot 업데이트 요청 객체")
    public static class Request {

        @ApiModelProperty(value = "업데이트 요청하는 닷 id")
        private Long dotId;

        @ApiModelProperty(value = "음악 url")
        private String musicUrl;

        @ApiModelProperty(value = "음악 이름")
        private String musicName;

        @ApiModelProperty(value = "음악 앨범 사진")
        private String albumArt;

        @ApiModelProperty(value = "음악 사이트 타입 (YOUTUBE)")
        private String siteType;

        @ApiModelProperty(value = "위치 별명")
        private String regionNickname;

        @ApiModelProperty(value = "한줄 코멘트")
        private String comment;
    }

    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    @ApiModel(value = "Dot 업데이트 반환 객체", description = "Dot 업데이트 반환 객체")
    public static class Response {

        @ApiModelProperty(value = "업데이트 된 닷 id")
        private Long dotId;

        public static Response create(Long dotId) {
            return new Response(dotId);
        }
    }


}
