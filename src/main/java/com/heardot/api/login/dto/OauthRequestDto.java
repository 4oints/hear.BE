package com.heardot.api.login.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@ApiModel(value = "Oauth 로그인 요청 객체", description = "Oauth 로그인을 위한 요청 객체")
public class OauthRequestDto {

    @ApiModelProperty(value = "소셜 로그인 타입(GOOGLE, KAKAO)", required = true)
    private String socialType;

    @ApiModelProperty(value = "기본 프로필 이미지 경로", required = true)
    private String defaultProfileImageUrl;

}
