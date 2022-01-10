package com.heardot.api.fcm.dto;

import com.heardot.domain.dot.Dot;
import com.heardot.util.DateTimeUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.format.DateTimeFormatter;

public class PushClickDto {

    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    @Builder
    @ApiModel(value = "푸시 클릭 반환 객체", description = "푸시 클릭 반환 객체")
    public static class Response {

        @ApiModelProperty(value = "닷 ID")
        private Long dotId;

        @ApiModelProperty(value = "위치 별명")
        private String locationNickname;

        @ApiModelProperty(value = "저장한 날짜")
        private String regTime;

        @ApiModelProperty(value = "한 줄 코멘트")
        private String comment;

        @ApiModelProperty(value = "아티스트")
        private String artist;

        @ApiModelProperty(value = "노래 제목")
        private String musicName;

        @ApiModelProperty(value = "사진 id")
        private String pictureId;

        @ApiModelProperty(value = "노래 url")
        private String musicUrl;

        @ApiModelProperty(value = "Dot QR 코드")
        private String qrCode;

        public static Response create(Dot dot) {
            return Response.builder()
                    .dotId(dot.getDotId())
                    .locationNickname(dot.getRegionNickname())
                    .regTime(DateTimeUtils.convertToString(dot.getRegTime().toLocalDate()))
                    .comment(dot.getComment())
                    .artist(dot.getMusic().getArtist())
                    .musicName(dot.getMusic().getMusicName())
                    .pictureId(dot.getPictureId())
                    .musicUrl(dot.getMusic().getMusicUrl())
                    .qrCode(null) // TODO qr code 추가
                    .build();
        }
    }

    /*@Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    @ApiModel(value = "푸시 클릭 요청 객체", description = "푸시 클릭 요청 객체")
    public static class Request {

        @ApiModelProperty(value = "위도")
        private String latitude;

        @ApiModelProperty(value = "경도")
        private String longitude;
    }*/
}
