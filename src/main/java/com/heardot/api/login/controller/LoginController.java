package com.heardot.api.login.controller;

import com.heardot.api.login.dto.OauthRequestDto;
import com.heardot.api.login.dto.ResponseJwtTokenDto;
import com.heardot.api.login.service.LoginService;
import com.heardot.domain.member.constant.SocialType;
import com.heardot.exception.InvalidParameterException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {

    private final LoginService loginService;

    @PostMapping(value = "/oauth/login" , headers = { "Content-type=application/json" }, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "OAuth 로그인 API", description = "OAuth Access 토큰으로 로그인 시 JWT 토큰 반환, 현재 socialType은 GOOGLE, KAKAO만 구현 완료")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", defaultValue ="access token", dataType = "string", value = "access token", required = true, paramType = "header")
    })
    // TODO param 정보 입력
    //@ApiImplicitParam(name = "acceessToken", dataType = "body", required = true, dataTypeClass = com.sloth.api.oauth.dto.OauthRequestDto.class),
    //@ApiImplicitParam(name = "socialType", dataType = "body", required = true, dataTypeClass = com.sloth.api.oauth.dto.OauthRequestDto.class)
    public ResponseEntity<ResponseJwtTokenDto> login(@RequestBody OauthRequestDto oauthRequestDto, HttpServletRequest httpServletRequest) {

        String accessToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

        if(StringUtils.isBlank(accessToken)) {
            throw new InvalidParameterException("토큰값을 입력해주세요");
        }

        if(!SocialType.isSocialType(oauthRequestDto.getSocialType())) {
            throw new InvalidParameterException("잘못된 소셜 타입입니다. 'GOOGLE', 'KAKAO' 중에 입력해주세요");
        }

        SocialType socialType = SocialType.from(oauthRequestDto.getSocialType());
        ResponseJwtTokenDto responseJwtTokenDto = loginService.login(accessToken, socialType, oauthRequestDto.getDefaultProfileImageUrl());

        return ResponseEntity.ok(responseJwtTokenDto);
    }
}
