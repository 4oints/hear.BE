package com.heardot.api.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class DefaultProfileUrl {

    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    @ApiModel(value = "기본 프로필 이미지로 변경 요청 객체", description = "기본 프로필 이미지로 변경 요청 객체")
    public static class Request {
        @ApiModelProperty("기본 프로필 이미지 url")
        private String defaultProfileImageUrl;
    }
}
