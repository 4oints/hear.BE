package com.heardot.api.fcm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

public class PushDto {

    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    @ApiModel(value = "푸시 알림 요청 객체", description = "푸시 알림 요청 객체")
    public static class Request {

        @ApiModelProperty(value = "클라이언트 토큰")
        private String targetToken;

        @ApiModelProperty(value = "위도")
        private String latitude;

        @ApiModelProperty(value = "경도")
        private String longitude;
    }

}
