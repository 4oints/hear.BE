package com.heardot.api.login.service;

import com.heardot.api.login.dto.ResponseJwtTokenDto;
import com.heardot.config.auth.TokenProvider;
import com.heardot.config.auth.dto.OAuthAttributes;
import com.heardot.config.auth.dto.TokenDto;
import com.heardot.domain.member.constant.SocialType;
import com.heardot.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class LoginService {

    private final TokenProvider tokenProvider;
    private final ModelMapper modelMapper;
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    /**
     * OAuth 로그인
     * @param accessToken
     * @param socialType
     * @return
     */
    public ResponseJwtTokenDto login(String accessToken, SocialType socialType) {

        // 소셜 회원 정보 조회
        OAuthAttributes oAuthAttributes = getSocialUserInfo(accessToken, socialType);

        // 토큰 생성
        TokenDto tokenDto = tokenProvider.createTokenDto(oAuthAttributes.getEmail());

        // 회원가입
        memberService.saveMember(oAuthAttributes, tokenDto);

        return modelMapper.map(tokenDto, ResponseJwtTokenDto.class);
    }

    private OAuthAttributes getSocialUserInfo(String accessToken, SocialType socialType) {
        SocialApiService socialApiService = SocialApiServiceFactory.getSocialApiService(socialType);
        OAuthAttributes oAuthAttributes = socialApiService.getUserInfo(accessToken);
        return oAuthAttributes;
    }
}
